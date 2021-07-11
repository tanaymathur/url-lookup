package com.stylight.urllookup.controller;


import com.stylight.urllookup.service.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class PrettyUrlController {

    Translator translator;

    @Autowired
    public PrettyUrlController(Translator translator){
        this.translator = translator;
    }

    @GetMapping("/pretty-urls")
    public Map<String, String> getPrettyUrl(@RequestBody List<String> paramterisedUrlList){
        return paramterisedUrlList.parallelStream()
                .collect(Collectors.toMap(Function.identity(),translator::getPrettyUrl));
    }

    @GetMapping("/parameterized-urls")
    public Map<String, String> getParamterisedUrlList(@RequestBody List<String> prettyUrls){
        return prettyUrls.parallelStream()
                .collect(Collectors.toMap(Function.identity(),translator::getParameterizedUrl));
    }
}
