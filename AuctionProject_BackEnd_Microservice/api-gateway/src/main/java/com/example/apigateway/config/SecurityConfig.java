package com.example.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Value("${signer.key}")
    private String signerKey;
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .csrf().disable()
                .authorizeExchange(exchange ->
                        exchange
                                .pathMatchers("/eureka/**").permitAll()
                                .pathMatchers("/api/user/auth").permitAll()
                                .anyExchange()
                                .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtDecoder(jwtDecoder())));
        return serverHttpSecurity.build();
    }
    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
        return NimbusReactiveJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
}
