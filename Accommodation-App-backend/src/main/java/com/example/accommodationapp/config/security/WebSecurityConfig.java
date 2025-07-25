package com.example.accommodationapp.config.security;

import com.example.accommodationapp.security.CustomUsernamePasswordAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


import java.util.List;

@Profile("test")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;

    public WebSecurityConfig(CustomUsernamePasswordAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of(
                "http://localhost:3000/",     // за локално девелопирање
                "http://frontend.local/"      // за production/K8s ingress
        ));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000/", "http://frontend.local"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
//        corsConfiguration.setAllowCredentials(true);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(
                        corsConfigurationSource()));
//                .authorizeHttpRequests(requests -> requests.requestMatchers(
//                                "/api/accommodations",
//                                "/api/hosts",
//                                "/api/countries",
//                                "/api/user/login",
//                                "/api/user/register"
//                        ).permitAll()
//                        .anyRequest().hasAnyRole("HOST","USER"))
//                .formLogin((form) -> form.loginProcessingUrl(
//                                "/api/user/login")
//                        .permitAll()
//                        .failureUrl("/api/user/login?error=BadCredentials")
//                        .defaultSuccessUrl(
//                                "/swagger-ui/index.html",
//                                true
//                        ))
//                .logout((logout) -> logout.logoutUrl("/api/user/logout")
//                        .clearAuthentication(true)
//                        .invalidateHttpSession(
//                                true)
//                        .deleteCookies("JSESSIONID")
//                        .logoutSuccessUrl("/api/user/login"))
//                .exceptionHandling((ex) -> ex.accessDeniedPage(
//                        "/access_denied"));
        return http.build();
    }


    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
                AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        return authenticationManagerBuilder.build();
    }

}