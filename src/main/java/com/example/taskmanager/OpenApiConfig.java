package com.example.taskmanager;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Manager API")
                        .version("1.0")
                        .description("Professional API for daily task management. Includes complete CRUD functionality and H2 database persistence.")
                        .contact(new Contact()
                                .name("Pau Rovira Rosaleny")
                                .url("https://www.linkedin.com/in/pau-rovira-rosaleny-142448308")));
    }
}