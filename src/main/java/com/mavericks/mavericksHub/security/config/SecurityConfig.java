package com.mavericks.mavericksHub.security.config;


import com.mavericks.mavericksHub.security.filter.CustomerUsernameAndPasswordAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@AllArgsConstructor
@Configuration
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        var authenticationFilter = new CustomerUsernameAndPasswordAuthFilter(authenticationManager);
        authenticationFilter.setFilterProcessesUrl("/api/v1/auth");
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(c->c.disable())
                .addFilterAt(authenticationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c->c.anyRequest().permitAll())
                .build();
    }

}
