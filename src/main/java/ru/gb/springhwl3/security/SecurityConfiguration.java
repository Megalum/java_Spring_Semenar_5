package ru.gb.springhwl3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
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
                        .requestMatchers("ui/books/**").hasAnyAuthority("user", "admin")
                        .requestMatchers("ui/**").hasAuthority("admin")
                        .anyRequest().denyAll()
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
