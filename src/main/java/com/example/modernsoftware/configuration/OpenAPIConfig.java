package com.example.modernsoftware.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${tuandanh.openapi.dev-url}")
    private String devUrl;

    @Value("${tuandanh.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("nguyentuananh200601@gmail.com");
        contact.setName("TuanDanh");
        contact.setUrl("https://www.tuandanh.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        SecurityScheme securityScheme = new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                .bearerFormat("JWT");
        Components components = new Components().addSecuritySchemes("bearAuth", securityScheme);

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearAuth");


        Info info = new Info()
                .title("User Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage users.").termsOfService("https://www.tuandanh.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer))
                .components(components)
                .security(List.of(securityRequirement));

    }
}
