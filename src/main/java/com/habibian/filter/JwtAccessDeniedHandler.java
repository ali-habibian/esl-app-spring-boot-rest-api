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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

/**
 * JwtAccessDeniedHandler is responsible for handling access denied exceptions and serving an appropriate response
 * with the access denied details.
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Handles the access denied exception by writing a JSON response with the access denied details to the output stream.
     *
     * @param request   The HttpServletRequest object representing the request.
     * @param response  The HttpServletResponse object representing the response.
     * @param exception The AccessDeniedException that occurred.
     * @throws IOException If an I/O error occurs while writing the response.
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException {
        // Create an HttpResponse object with the access denied details
        HttpResponse httpResponse = new HttpResponse(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED,
                HttpStatus.UNAUTHORIZED.getReasonPhrase().toUpperCase(),
                SecurityConstant.ACCESS_DENIED_MESSAGE
        );

        // Set the response content type to JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // Get the response output stream
        OutputStream outputStream = response.getOutputStream();

        // Write the HttpResponse object as JSON to the output stream using an ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, httpResponse);
        outputStream.flush();
    }
}
