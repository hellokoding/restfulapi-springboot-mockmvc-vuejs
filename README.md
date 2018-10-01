# Restful API example with Spring Boot, Integration Test with MockMVC, UI Integration with VueJS

### Guide
https://hellokoding.com/restful-apis-example-with-spring-boot-integration-test-with-mockmvc-ui-integration-with-vuejs/

### Stack
- Spring Boot
- Java 8
- Maven 3
- Integration test
- VueJS

### Tools you need
- Java 8
- Maven 3

### Build and run
`mvn clean spring-boot:run`

### Run the tests only
`mvn clean verify`

### Test the APIs
- Get all stocks `curl http://localhost:8080/api/v1/stocks`
- Get stock 1 `curl http://localhost:8080/api/v1/stocks/1`
- Update price of stock 1 `curl -X PUT -d 'currentPrice=123' http://localhost:8080/api/v1/stocks/1`
- Create a new stock `curl -H "Content-Type: application/json" -X POST -d '{"name":"S11","currentPrice":"11"}' http://localhost:8080/api/v1/stocks`
- Stock list front end: access to `http://localhost:8080`

