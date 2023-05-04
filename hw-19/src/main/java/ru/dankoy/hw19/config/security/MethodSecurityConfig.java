package ru.dankoy.hw19.config.security;


import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity(
    securedEnabled = true
)
public class MethodSecurityConfig {

}
