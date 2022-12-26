//package com.demo.scg.config;
//
///**
// *
// */
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
//import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
//import org.springframework.security.oauth2.server.resource.introspection.NimbusReactiveOpaqueTokenIntrospector;
//import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector;
//
//import reactor.core.publisher.Mono;
//
///**
// * Custom ReactiveTokenIntrospector to map realm roles into Spring GrantedAuthorities
// *
// */
//public class KeycloakReactiveTokenInstrospector implements ReactiveOpaqueTokenIntrospector {
//
////    private final ReactiveOpaqueTokenIntrospector delegate;
////
////    public KeycloakReactiveTokenInstrospector() {
////        this.delegate = delegate;
////    }
//
//    private ReactiveOpaqueTokenIntrospector delegate =
//            new NimbusReactiveOpaqueTokenIntrospector("http://localhost:8080/auth/realms/demo/protocol/openid-connect/token/introspect",
//                    "demo-client",
//                    "83f3e948-3f8c-49da-af7f-87a42d5ea6fd");
//    @Override
//    public Mono<OAuth2AuthenticatedPrincipal> introspect(String token) {
//
//        return delegate.introspect(token)
//                .map(this::mapPrincipal);
//    }
//
//    protected OAuth2AuthenticatedPrincipal mapPrincipal(OAuth2AuthenticatedPrincipal principal) {
//
//        return new DefaultOAuth2AuthenticatedPrincipal(
//                principal.getName(),
//                principal.getAttributes(),
//                extractAuthorities(principal));
//    }
//
//    protected Collection<GrantedAuthority> extractAuthorities(OAuth2AuthenticatedPrincipal principal) {
//
//        //
//        Map<String,List<String>> realm_access = principal.getAttribute("realm_access");
//        List<String> roles = realm_access.getOrDefault("roles", Collections.emptyList());
//        List<GrantedAuthority> rolesAuthorities = roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//
//        Set<GrantedAuthority> allAuthorities = new HashSet<>();
//        allAuthorities.addAll(principal.getAuthorities());
//        allAuthorities.addAll(rolesAuthorities);
//
//        return allAuthorities;
//    }
//
//    @Bean
//    public ReactiveOpaqueTokenIntrospector introspector(){
//        return new KeycloakReactiveTokenInstrospector();
//    }
//
//}
