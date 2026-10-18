# Modern API Basic

Spring Boot 3.x 기반의 User CRUD API 예제 프로젝트입니다. 원문 예시의 Java 17 설정 대신 Java 21로 실행되도록 구성했습니다.

## Requirements

- Java 21

## Run

```bash
./gradlew bootRun
```

## Test

```bash
./gradlew test
```

## API

- `POST /api/v1/users`
- `GET /api/v1/users/{userId}`
- `GET /api/v1/users?page=0&size=20&sort=id,desc`
- `PATCH /api/v1/users/{userId}`
- `DELETE /api/v1/users/{userId}`

H2 Console: `http://localhost:8080/h2-console`
