package com.stylight.urllookup.service;

import com.stylight.urllookup.repository.ParameterizedUrlTrie;
import com.stylight.urllookup.repository.UrlMappingRepo;
import com.stylight.urllookup.repository.PrettyUrlTrie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class Translator {

    ParameterizedUrlTrie parameterizedUrlTrie;
    PrettyUrlTrie prettyUrlTrie;
    UrlMappingRepo repo ;

    @Autowired
    public Translator(UrlMappingRepo urlMappingRepo,
                      ParameterizedUrlTrie parameterizedUrlTrie,
                      PrettyUrlTrie prettyUrlTrie){
        this.repo = urlMappingRepo;
        this.parameterizedUrlTrie = parameterizedUrlTrie;
        this.prettyUrlTrie =  prettyUrlTrie;
        repo.getPrettyUrlMap().keySet().forEach(e -> parameterizedUrlTrie.insert(e));
        repo.getPrettyUrlMap().values().forEach(e -> prettyUrlTrie.insert(e));
    }

    public String getPrettyUrl(String parameterizedUrl) {
        Optional<String> url = repo.lookup(parameterizedUrl);
        if (url.isPresent())
            return url.get();
        else {
            Optional<String> result = Optional.ofNullable(parameterizedUrlTrie.search(parameterizedUrl));
            if (result.isPresent()) {
                Optional<String> prettyUrl = repo.lookup(result.get());
                if (prettyUrl.isPresent()) {
                    String concatedUrl = prettyUrl.get().concat(createUrlSuffix(parameterizedUrl, result.get()));
                    return reformatUrl(concatedUrl);
                }
            }
        }
        return parameterizedUrl;
    }

    private String reformatUrl(String url) {
        char[] chars = url.toCharArray();
        for(int index = 0;index<chars.length;index++){
            if(chars[index]=='&'){
                chars[index] = '?';
                break;
            }else if(chars[index]=='?') break;
        }
        return new String(chars);
    }

    private String createUrlSuffix(String url, String result) {
        return url.substring(result.length());
    }

    public String getParameterizedUrl(String prettyUrl) {
        Optional<String> url = repo.reverseLookUp(prettyUrl);
        if (url.isPresent())
            return url.get();
        else {
            Optional<String> result = Optional.ofNullable(prettyUrlTrie.search(prettyUrl));
            if (result.isPresent()) {
                Optional<String> parameterizedUrl = repo.reverseLookUp(result.get());
                if (parameterizedUrl.isPresent()) {
                    String concatedUrl = parameterizedUrl.get().concat(createUrlSuffix(prettyUrl, result.get())).replace("?","&");
                    return reformatUrl(concatedUrl);
                }
            }
        }
        return prettyUrl;
    }
}
