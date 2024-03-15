package com.sawtooth.security;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@NonNullApi
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final Logger logger;

    @Autowired
    public JwtFilter(JwtUtils jwtUtils, Logger logger) {
        this.jwtUtils = jwtUtils;
        this.logger = logger;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = (String) request.getAttribute("Authorization"), username = null, token;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtUtils.getUsername(token);
            }
            catch (Exception exception) {
                logger.atDebug().log(exception.getMessage());
                SecurityContextHolder.getContext().setAuthentication(null);
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                        username, null, jwtUtils.getRoles(token)
                ));
        }
        filterChain.doFilter(request, response);
    }
}
