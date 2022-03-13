
<h2>
<span style="color:#a9910e">Building the JAR file:</span>
</h2>

```
Run "mvn package" command in your IDE terminal or use the Maven interface.
```
[ArticleAPI.jar for Mac(Intel)](ArticleAPI.jar)



<h2>
<span style="color:#a9910e">Starting the JAR file:</span>
</h2>

```
Get to the same directory in your terminal where is the JAR file located and start it with
java -jar ArticleAPI.jar
```

<h2>
<span style="color:#a9910e">Supporting requests:</span>
</h2>

```http request
[GET] localhost:8080/articles/{articleId}
[GET] localhost:8080/tags/{tag}/{date} (date ex. 20220313) //2022 March 13
[POST] localhost:8080/articles (+JSON)
```
```json
{ 
"title": "titleExample",
"date" : "2022-03-13",
"body" : "bodyExample",
"tags" : ["tagExample1", "tagExample2"]
}
```
<h2>
<span style="color:#a9910e">App DB access: </span>
</h2>

[http://localhost:8080/articleDB]()
```
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:ArticleDB
User Name: sa
Password: <empty>
```


