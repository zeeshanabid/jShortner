# jShortner

# Generate short URL

*Example:*

`POST /shorten`

Request body
```
Content-Type: application/json
{"url": "http://google.com"}
```

Response body
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Mon, 29 Jan 2018 23:14:06 GMT

{"url":"http://google.com","shortURL":"/4xm"}
```

# Redirect short URL

*Example:*

`GET /4xm`

# Run application

`mvn spring-boot:run`

# Run tests

`mvn test`
