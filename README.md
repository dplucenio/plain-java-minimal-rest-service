# :coffee: plain-java-minimal-rest-service

Minimalist java REST service without any frameworks like `Spring` for studying purposes, to better
understand what happens underneath such libraries. Inspired by [Marco Behler](https://www.marcobehler.com/) tutorial. The goal is to keep this as simple as possible
and cover:

- [x] embedded Tomcat on application
- [x] bundled executable jar
- [x] JSON manipulation and transformation 
- [x] dependency injection

Progress is going to be stored on different branches, starting from `1-embedded-tomcat-bundle-jar`

This projects uses `maven` and Java 11.

To create the executable jar, run:

```shell script
mvn clean package
```

To run the application:

```shell script
java -jar plain-java-minimal-rest-service-1.0-SNAPSHOT.jar
```
