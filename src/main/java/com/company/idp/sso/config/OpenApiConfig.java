package com.company.idp.sso.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "IDP SSO Microservice API",
        version = "v1",
        description = "Realm-routed SSO endpoints (authenticate, forgot flows, profile, password updates).",
        contact = @Contact(name = "SSO Team")
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Local")
    }
)
public class OpenApiConfig { }
