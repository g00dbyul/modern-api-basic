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

## API Documentation

애플리케이션 실행 후 아래 주소에서 API 문서를 확인할 수 있습니다.

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

Swagger UI에서 요청·응답 형식과 오류 코드를 확인하고, **Try it out**으로 API를 호출할 수 있습니다.
목록 조회는 `page`(0부터 시작), `size`, `sort`(예: `id,desc`)를 지원합니다.
등록·수정·삭제 요청은 실제 실행 중인 데이터에 반영됩니다.
