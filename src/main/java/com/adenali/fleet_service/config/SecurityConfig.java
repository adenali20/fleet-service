package com.adenali.fleet_service.config;

import com.adenali.fleet_service.filter.GatewaySecretFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final GatewaySecretFilter gatewaySecretFilter;

    public SecurityConfig(GatewaySecretFilter gatewaySecretFilter) {
        this.gatewaySecretFilter = gatewaySecretFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // ✅ allow Swagger (WITH gateway prefix)
                        .requestMatchers(
                                "/api/fleetservice/swagger-ui/**",
                                "/api/fleetservice/v3/api-docs/**",
                                "/api/fleetservice/swagger-ui.html"
                        ).permitAll()

                        // 🔐 everything else requires gateway role
                        .anyRequest().hasRole("GATEWAY")
                )
                .addFilterBefore(
                        gatewaySecretFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
