package com.sawtooth.security;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@NonNullApi
public class JwtCookieFilter extends OncePerRequestFilter {
    @Value("${security.jwt.cookie.name}")
    private String jwtCookieName;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        if (cookies != null)
            for (Cookie cookie : cookies)
                if (Objects.equals(cookie.getName(), jwtCookieName))
                    request.setAttribute("Authorization", "Bearer " + cookie.getValue());
        filterChain.doFilter(request, response);
    }
}
