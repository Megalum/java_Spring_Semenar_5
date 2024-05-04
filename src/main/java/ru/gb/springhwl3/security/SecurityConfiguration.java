package ru.gb.springhwl3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(source -> {
            Map<String, Object> claim = source.getClaim("realm_access");
            List<String> roles = (List<String>) claim.get("roles");
            Object SimpleGrantedAuthority;
            return roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        });

        return httpSecurity
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("ui/**").hasAuthority("admin")
                        .requestMatchers("ui/books/**").authenticated()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "book/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "book/**").permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
