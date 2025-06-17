package com.mikhalenok.monitor.sensors.infrastructure.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Monitor sensor API",
                version = "1.0",
                description = "API for managing sensors"
        )
)
public class OpenAPIConfig {
}
