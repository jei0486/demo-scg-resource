package com.demo.scg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        // oauth2 Resource
        http.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .opaqueToken(opaqueToken -> opaqueToken
                                .introspectionUri("http://localhost:8080/auth/realms/demo/protocol/openid-connect/token/introspect")
                                .introspectionClientCredentials("demo-client", "83f3e948-3f8c-49da-af7f-87a42d5ea6fd")
                        )
                );

        http.csrf().disable();

        return http.build();
    }
}
