package com.example.PokemonAPI.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(form -> form.
                loginPage("/login").
                defaultSuccessUrl("/index", true).
                permitAll());

//        http.logout(logout -> logout.logoutSuccessUrl("/index").permitAll());

        return http.authorizeHttpRequests(authz -> authz
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/index").hasRole("USER")
                        .requestMatchers("/filter").hasRole("USER")
                        .requestMatchers("/user/new").permitAll()
                        .requestMatchers("/user/edition").hasRole("ADMIN")
                        .requestMatchers("/user/profile").hasRole("USER")
                        .requestMatchers("/user/profileModal").hasRole("USER")
                        .requestMatchers("/user/addFavorite").hasRole("USER")
                        .requestMatchers("/pokemon/addGroup").hasRole("ADMIN")
                        .requestMatchers("/pokemon/deleteGroup").hasRole("ADMIN")
                        .anyRequest().authenticated()).
                build();
    }

    @Bean
    static RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withDefaultRolePrefix()
                .role("ADMIN").implies("USER")
                .build();
    }

    @Bean
    static MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }
}
