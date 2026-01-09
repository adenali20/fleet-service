package com.adenali.fleet_service.config;


import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQlConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        // This registers the 'JSON' scalar so it can map to your Map<String, Object>
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.Json);
    }
}
