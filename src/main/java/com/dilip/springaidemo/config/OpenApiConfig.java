package com.dilip.springaidemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Dilip Kumar B K",
                        email = "sudheer0418@gmail.com",
                        url = "https://dilip-sudheer.netlify.app/"
                ),
                title = "QuickAI - Using Hugging Face Inference APIs",
                version = "1.0.0",
                description = "Demo project showcasing the integration of external Service (Inference API) and leveraging the power of AI",
                license = @License(
                        name = "Dilip",
                        url = "https://github.com/dilip0418"
                ),
                termsOfService = "Terms of Service"
        ),
        servers = {
                @Server(
                        description = "Development",
                        url = "http://localhost:8080/"
                ),
                @Server(
                        description = "Production",
                        url = "https://prod-env/" //web address of the prod env
                ),
        }
)
public class OpenApiConfig {
}
