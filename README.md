# idp-sso-microservice (Starter)

Spring Boot + Java + Gradle starter for realm-routed login related endpoints.
- Stateless microservice (no session stores here).
- Every endpoint calls downstream service based on `realm` using **WebClient**.
- JSON-only APIs, validation, structured error responses, logging (correlation-id), tests, Jacoco, Sonar.

## Endpoints
- POST /authenticate
- POST /forgotpassword
- POST /forgetusername
- POST /updatepassword
- POST /updateprofile
- POST /passwordexpireupdate

## Run
```bash
./gradlew bootRun
```

## Test
```bash
./gradlew test
```

## Config
See `src/main/resources/application.yml` for realm mapping to downstream base URLs.

## OpenAPI / Swagger
- Swagger UI: `http://localhost:8080/swagger-ui`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
