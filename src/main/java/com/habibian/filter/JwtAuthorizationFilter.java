/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.filter;

import com.habibian.constant.SecurityConstant;
import com.habibian.utility.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * JwtAuthorizationFilter is responsible for validating and processing JWT tokens in the authorization header of incoming requests.
 * It checks the validity of the token, extracts the username and authorities, and sets the authentication in the security context.
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Constructs a JwtAuthorizationFilter with the specified JwtTokenProvider.
     *
     * @param jwtTokenProvider The JwtTokenProvider used for token validation and authentication.
     */
    public JwtAuthorizationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Performs the filtering logic for each incoming request.
     * It checks the authorization header for a valid JWT token, extracts the username and authorities from the token,
     * and sets the authentication in the security context.
     *
     * @param request     The HttpServletRequest object representing the request.
     * @param response    The HttpServletResponse object representing the response.
     * @param filterChain The FilterChain object for invoking the next filter in the chain.
     * @throws ServletException If any servlet-related errors occur during the filtering process.
     * @throws IOException      If an I/O error occurs during the filtering process.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Check if the request method is OPTIONS and set the response status to 200 (OK) if true
        if (request.getMethod().equalsIgnoreCase(SecurityConstant.OPTIONS_HTTP_METHOD)) {
            response.setStatus(HttpStatus.OK.value());
        } else {
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            // If the authorization header is missing or does not start with the token prefix, pass the request to the next filter
            if (authorizationHeader == null || !authorizationHeader.startsWith(SecurityConstant.TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = authorizationHeader.substring(SecurityConstant.TOKEN_PREFIX.length());
            String username = jwtTokenProvider.getSubject(token);

            // Validate the token and check if there is no existing authentication in the security context
            if (jwtTokenProvider.isTokenValid(username, token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Extract the authorities from the token
                List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(token);
                // Create an authentication object with the username, authorities, and the current request
                Authentication authentication = jwtTokenProvider.getAuthentication(username, authorities, request);
                // Set the authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // Clear the security context if the token is invalid or an authentication already exists
                SecurityContextHolder.clearContext();
            }
        }

        // Pass the request and response to the next filter in the chain
        filterChain.doFilter(request, response);
    }
}
