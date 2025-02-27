package com.example.devicesapi.config;

import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info( new Info()
                .title("Devices API by Amer Jaradeh")
                .version("1.0.0")
                .description("Documentation for Devices API by Amer Jaradeh"));
    }
}
