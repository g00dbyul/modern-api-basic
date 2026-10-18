package com.godbyul.modern_api.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Modern API Basic",
        version = "v1",
        description = "사용자 CRUD API 문서"
))
public class OpenApiConfig {
}
