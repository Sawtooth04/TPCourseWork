package com.sawtooth.configurations;

import com.sawtooth.security.JwtCookieFilter;
import com.sawtooth.security.JwtFilter;
import com.sawtooth.security.UserService;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
@PropertySource("classpath:security.properties")
public class SecurityConfiguration {
    private final JwtFilter jwtFilter;
    private final JwtCookieFilter jwtCookieFilter;

    @Autowired
    public SecurityConfiguration(JwtFilter jwtFilter, JwtCookieFilter jwtCookieFilter) {
        this.jwtFilter = jwtFilter;
        this.jwtCookieFilter = jwtCookieFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(customizer -> {
                    customizer.anyRequest().permitAll();
                })
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.httpStrictTransportSecurity(HeadersConfigurer.HstsConfig::disable))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtCookieFilter, JwtFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
