package com.uniovi.sdi.sdi2526611spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/images/**", "/script/**", "/", "/signup", "/login/**").permitAll()

                        // SEGURIDAD EJERCICIO 1 (Revisión)
                        .requestMatchers("/mark/add").hasAuthority("ROLE_PROFESSOR")
                        .requestMatchers("/mark/edit/*").hasAuthority("ROLE_PROFESSOR")
                        .requestMatchers("/mark/delete/*").hasAuthority("ROLE_PROFESSOR")

                        .requestMatchers("/professor/add", "/professor/edit/*", "/professor/delete/*").hasAuthority("ROLE_ADMIN")

                        .requestMatchers("/professor/details/*").hasAnyAuthority("ROLE_PROFESSOR", "ROLE_ADMIN")

                        .requestMatchers("/professor/list").authenticated()


                        .requestMatchers("/mark/**").hasAnyAuthority("ROLE_PROFESSOR","ROLE_STUDENT","ROLE_ADMIN")
                        .requestMatchers("/user/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/home", true)
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/home")
                        .permitAll()
                ).securityContext(securityContext -> securityContext.requireExplicitSave(true))

        ;
        return http.build();
    }

}
