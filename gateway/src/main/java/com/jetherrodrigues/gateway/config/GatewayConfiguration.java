package com.jetherrodrigues.gateway.config;

import io.github.jhipster.config.JHipsterProperties;

import com.jetherrodrigues.gateway.gateway.accesscontrol.AccessControlFilter;
import com.jetherrodrigues.gateway.gateway.responserewriting.SwaggerBasePathRewritingFilter;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Configuration
    public static class SwaggerBasePathRewritingConfiguration {

        @Bean
        public SwaggerBasePathRewritingFilter swaggerBasePathRewritingFilter() {
            return new SwaggerBasePathRewritingFilter();
        }
    }

    @Configuration
    public static class AccessControlFilterConfiguration {

        @Bean
        public AccessControlFilter accessControlFilter(RouteLocator routeLocator, JHipsterProperties jHipsterProperties) {
            return new AccessControlFilter(routeLocator, jHipsterProperties);
        }
    }

}
