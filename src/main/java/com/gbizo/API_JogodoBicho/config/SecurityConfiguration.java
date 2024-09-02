package com.gbizo.API_JogodoBicho.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .authorizeHttpRequests(auth -> {
                        auth.requestMatchers("/api/contraventor/selectAll").permitAll();
                        auth.requestMatchers("/api/cliente/selectAll").permitAll();
                        auth.requestMatchers("/api/cliente/select/{id_cli}").permitAll();
                        auth.requestMatchers("/api/contraventor/select/{id_cont}").permitAll();
                        auth.requestMatchers("/api/contraventor/create").permitAll();
                        auth.anyRequest().authenticated();
                    })
                    //.oauth2Client(Customizer.withDefaults())
                    //.formLogin(Customizer.withDefaults())
                    .build();
    }
}
