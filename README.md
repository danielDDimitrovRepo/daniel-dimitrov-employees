# Employee History Matcher

## Setting & testing the project
* For IDEs - set project SDK and language level to OpenJDK 21.0.1
* Build and run tests:
>mvn package
* Run as Spring Boot App
> mvn spring-boot:run
* Test the endpoint:
>curl -F csv=@{your-local-path}\daniel-dimitrov-employees\src\test\resources\controller\job-history.csv http://localhost:8080/job-history/longest-pair