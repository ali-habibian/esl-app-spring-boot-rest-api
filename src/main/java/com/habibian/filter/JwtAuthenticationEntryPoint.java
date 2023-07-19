/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.habibian.constant.SecurityConstant;
import com.habibian.domain.HttpResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

/**
 * JwtAuthenticationEntryPoint is responsible for handling authentication exceptions
 * and serving as the entry point for unauthorized requests.
 */
@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    /**
     * Handles the commencement of an authentication exception.
     * It writes a JSON response with the forbidden details to the output stream.
     *
     * @param request   The HttpServletRequest object representing the request.
     * @param response  The HttpServletResponse object representing the response.
     * @param exception The AuthenticationException that occurred.
     * @throws IOException If an I/O error occurs while writing the response.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        // Create an HttpResponse object with the forbidden details
        HttpResponse httpResponse = new HttpResponse(
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN,
                HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(),
                SecurityConstant.FORBIDDEN_MESSAGE
        );

        // Set the response content type to JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        // Get the response output stream
        OutputStream outputStream = response.getOutputStream();

        // Write the HttpResponse object as JSON to the output stream using an ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, httpResponse);
        outputStream.flush();
    }
}
