# Getting Started

### URL Lookup Application
This application expose two rest endpoint which are used in translation to Pretty Url

### Overview
The application uses Database to store the mapping of URls. Exact queries will be stored as individual key in the hashmap (local) or caching server/redis (in production). For searching the key, Trie Data Strucutre is implemented for improving the efficiency. Whenever any new query is populated in Db, subsequently it needs to be inserted into the cache and Trie , and the application will take care of those operations.



### Installation
Assuming java and maven are already installed on the machine. If not, please [click here] .
```$xslt
mvn clean install
```
### Starting Application
```$xslt
mvn spring-boot:run
```

### Features

Translating URL to create pretty URL

```
curl -X GET \
  http://localhost:8080/v1/pretty-urls \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -d '[
    "/products?gender",
    "/products?gender=female&tag=123&tag=1234&tag=5678&tag=1231&&tag=12321",
    "/products?gender=male"
]'
```

Translating Pretty Url to original Url

```$xslt
curl -X GET \
  http://localhost:8080/v1/parameterized-urls \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -d '["/Fashion/","/Boat--Shoes/","/Women/Shoes/?tag=5678"]'
```

### Swagger

APi Documentation can be obtained in below link

```$xslt
http://localhost:8080/swagger-ui.html#/
```

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [click here]: <https://dev.to/jeannienguyen/how-to-install-java-jdk-and-maven-on-mac-os-168f>