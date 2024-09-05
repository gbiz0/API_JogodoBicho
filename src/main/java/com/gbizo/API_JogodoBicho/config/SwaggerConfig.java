package com.gbizo.API_JogodoBicho.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI custumOpenAPI() {
        return new OpenAPI().info(new Info().title("Jogo do Bicho API").description("sim, isso mesmo"));
    }
}
