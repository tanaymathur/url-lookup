package com.stylight.urllookup.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UrlMappingRepo {

    Map<String, String> prettyUrlMap;
    Map<String, String> reverseLookUp;

    public UrlMappingRepo(){
        prettyUrlMap = new HashMap<>();
        reverseLookUp = new HashMap<>();
        prettyUrlMap.put("/products","/Fashion/");
        prettyUrlMap.put("/products?tag=5678","/Boat--Shoes/");
        prettyUrlMap.put("/products?gender=female&tag=123&tag=1234","/Women/Shoes/");
        prettyUrlMap.put("/products?brand=123","/Adidas/");

        setReverseLookUp();
    }

    private void setReverseLookUp() {
        prettyUrlMap.forEach((key,value)->{
            reverseLookUp.put(value,key);
        });
    }

    public Optional<String> lookup(String paramterizedUrl){
        return Optional.ofNullable(prettyUrlMap.get(paramterizedUrl));
    }

    public Optional<String> reverseLookUp(String prettyUrl){
        return Optional.ofNullable(reverseLookUp.get(prettyUrl));
    }

    public Map<String, String> getPrettyUrlMap() {
        return prettyUrlMap;
    }
}
