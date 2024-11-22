package com.example.keycloakvaadinspring;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "Keycloak")
@PWA(
        name = "Vaadin keycloak",
        shortName = "CRM",
        offlinePath="offline.html",
        offlineResources = { "images/offline.png" }
)
public class KeycloakVaadinSpringApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(KeycloakVaadinSpringApplication.class, args);
    }

}
