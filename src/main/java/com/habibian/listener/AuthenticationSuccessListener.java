/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.listener;

import com.habibian.domain.UserPrincipal;
import com.habibian.service.LoginAttemptService;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * AuthenticationSuccessListener is responsible for listening to authentication success events and performing actions accordingly.
 * In this case, it listens to AuthenticationSuccessEvent and removes the user from the login attempt cache.
 */
@Component
public class AuthenticationSuccessListener {
    private final LoginAttemptService loginAttemptService;

    /**
     * Constructs an AuthenticationSuccessListener with the specified LoginAttemptService.
     *
     * @param loginAttemptService The LoginAttemptService used for handling login attempts.
     */
    public AuthenticationSuccessListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    /**
     * Listens to the AuthenticationSuccessEvent and removes the user from the login attempt cache.
     *
     * @param event The AuthenticationSuccessEvent representing the authentication success event.
     */
    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();

        // Check if the principal is of type UserPrincipal
        if (principal instanceof UserPrincipal user) {
            // Remove the user from the login attempt cache
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }
}
