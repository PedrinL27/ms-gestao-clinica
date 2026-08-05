package com.pedrin.gc_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(r -> r.path("/medico/**").uri("lb://gc-medicos"))
                .route(r -> r.path("/paciente/**").uri("lb://gc-pacientes"))
                .build();
    }
}
