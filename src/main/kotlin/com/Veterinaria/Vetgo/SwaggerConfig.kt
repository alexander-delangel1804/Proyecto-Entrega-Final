package com.Veterinaria.Vetgo

import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.OpenAPI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("API Veterinaria VetGo - Servicios a Domicilio")
                    .version("1.0.0")
                    .description("Documentación OpenAPI generada automáticamente con springdoc")
            )
    }
}