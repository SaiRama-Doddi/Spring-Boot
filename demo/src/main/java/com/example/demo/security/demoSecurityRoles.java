package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class demoSecurityRoles {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123") // {noop} is used for plaintext passwords (not recommended for production)
                .roles("EMPLOYEE")
                .build();

        UserDetails marry = User.builder()
                .username("marry")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails sasi = User.builder()
                .username("sasi")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, marry, sasi);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> 
                        configurer
                            .requestMatchers("/").hasRole("EMPLOYEE")
                             .requestMatchers("/leaders/**").hasRole("MANAGER")
                             .requestMatchers("/systems/**").hasRole("ADMIN")
                            .anyRequest().authenticated())
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage") // Custom login page
                                .loginProcessingUrl("/authenticateTheUser") // Processing endpoint
                                .permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}
