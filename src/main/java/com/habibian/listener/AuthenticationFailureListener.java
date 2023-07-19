/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.listener;

import com.habibian.service.LoginAttemptService;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * AuthenticationFailureListener is responsible for listening to authentication failure events and performing actions accordingly.
 * In this case, it listens to AuthenticationFailureBadCredentialsEvent and adds the user to the login attempt cache.
 */
@Component
public class AuthenticationFailureListener {
    private final LoginAttemptService loginAttemptService;

    /**
     * Constructs an AuthenticationFailureListener with the specified LoginAttemptService.
     *
     * @param loginAttemptService The LoginAttemptService used for handling login attempts.
     */
    public AuthenticationFailureListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    /**
     * Listens to the AuthenticationFailureBadCredentialsEvent and adds the user to the login attempt cache.
     *
     * @param event The AuthenticationFailureBadCredentialsEvent representing the authentication failure event.
     */
    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event) {
        Object principal = event.getAuthentication().getPrincipal();

        // Check if the principal is of type String (username)
        if (principal instanceof String username) {
            // Add the user to the login attempt cache
            loginAttemptService.addUserToLoginAttemptCache(username);
        }
    }
}
