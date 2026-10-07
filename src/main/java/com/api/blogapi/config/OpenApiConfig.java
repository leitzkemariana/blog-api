package com.api.blogapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI blogOpenApiConfig() {
        return new OpenAPI().info(new Info()
                        .title("Blog API - API Corporativa")
                        .version("1.0.0")
                        .description("API didática para posts e comentários"));
    }
}
