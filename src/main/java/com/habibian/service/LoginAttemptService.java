/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service;

/**
 * The LoginAttemptService interface provides methods to manage login attempts and track user login attempts.
 */
public interface LoginAttemptService {

    /**
     * Evicts a user from the login attempt cache.
     *
     * @param username The username of the user to evict from the login attempt cache.
     */
    void evictUserFromLoginAttemptCache(String username);

    /**
     * Adds a user to the login attempt cache.
     *
     * @param username The username of the user to add to the login attempt cache.
     */
    void addUserToLoginAttemptCache(String username);

    /**
     * Checks if a user has exceeded the maximum allowed login attempts.
     *
     * @param username The username of the user to check.
     * @return true if the user has exceeded the maximum allowed login attempts, false otherwise.
     */
    boolean hasExceededMaxAttempts(String username);
}
