package com.example.demo.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;



@OpenAPIDefinition(
        info = @Info(
                title = "Daily dev api",
                version = "1.0.0",
                description = "description"
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local test")
        }
)
@Configuration
public class OpenApiConfig {
}
