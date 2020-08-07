plain-java-minimal-rest-service
---

Minimalist java REST service without any frameworks like `Spring` for studying purposes, to better
understand what happens underneath such libraries. The goal is to keep this as simple as possible
and cover:

- [x] embedded Tomcat on application
- [x] bundled executable jar
- [ ] JSON manipulation and transformation 
- [ ] dependency injection

This projects uses `maven` and Java 11.

To create the executable jar, run:

```shell script
mvn clean package
```

To run the application:

```shell script
java -jar plain-java-minimal-rest-service-1.0-SNAPSHOT.jar
```