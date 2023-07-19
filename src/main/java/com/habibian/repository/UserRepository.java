/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.repository;

import com.habibian.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their username.
     *
     * @param username The username of the user
     * @return The User object if found, null otherwise
     */
    User findByUsername(String username);

    /**
     * Find a user by their email.
     *
     * @param email The email address of the user
     * @return The User object if found, null otherwise
     */
    User findByEmail(String email);
}
