package org.ticket.event_ticket_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.ticket.event_ticket_management.filters.UserProvisioningFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserProvisioningFilter userProvisioningFilter) throws Exception{
        http
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        //catch all
                        authorizationManagerRequestMatcherRegistry.anyRequest().authenticated())
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement(sessionManagementRequestMatcherRegistry ->sessionManagementRequestMatcherRegistry.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2ResourceServerRequestMatcherRegistry ->oauth2ResourceServerRequestMatcherRegistry.jwt(
                        Customizer.withDefaults()
                ))
                .addFilterAfter(userProvisioningFilter, BearerTokenAuthenticationFilter.class);
        return http.build();
    }
}
