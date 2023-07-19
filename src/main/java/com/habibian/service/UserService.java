/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service;

import com.habibian.domain.entity.User;
import com.habibian.dto.user.UserCreationRequest;
import com.habibian.dto.user.UserRegisterRequest;
import com.habibian.dto.user.UserDTO;
import com.habibian.dto.user.UserUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * The UserService interface provides methods to manage user registration, retrieval, update, and deletion.
 */
public interface UserService {

    /**
     * Registers a new user based on the provided user registration details.
     *
     * @param userRegisterRequest The UserRegisterRequest object containing the user registration details.
     * @return The UserDTO object representing the registered user.
     */
    UserDTO registerUser(UserRegisterRequest userRegisterRequest);

    /**
     * Retrieves a list of all users.
     *
     * @return A list of UserDTO objects representing the users.
     */
    List<UserDTO> getUsers();

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return The User object representing the found user, or null if not found.
     */
    User findUserByUsername(String username);

    /**
     * Finds a user by their email address.
     *
     * @param email The email address of the user to find.
     * @return The User object representing the found user, or null if not found.
     */
    User findUserByEmail(String email);

    /**
     * Adds a new user based on the provided user creation details.
     *
     * @param request The UserCreationRequest object containing the user creation details.
     * @return The UserDTO object representing the newly created user.
     * @throws IOException If an I/O error occurs during the process.
     */
    UserDTO addNewUser(UserCreationRequest request) throws IOException;

    /**
     * Updates a user based on the provided user update details.
     *
     * @param request The UserUpdateRequest object containing the user update details.
     * @return The UserDTO object representing the updated user.
     * @throws IOException If an I/O error occurs during the process.
     */
    UserDTO updateUser(UserUpdateRequest request) throws IOException;

    /**
     * Deletes a user by their username.
     *
     * @param username The username of the user to delete.
     * @throws IOException If an I/O error occurs during the process.
     */
    void deleteUser(String username) throws IOException;

    /**
     * Resets the password for a user based on their email address.
     *
     * @param email The email address of the user to reset the password for.
     */
    void resetPassword(String email);

    /**
     * Updates the profile image for a user.
     *
     * @param username         The username of the user.
     * @param newProfileImage  The MultipartFile object representing the new profile image.
     * @return The UserDTO object representing the user with the updated profile image.
     * @throws IOException If an I/O error occurs during the process.
     */
    UserDTO updateProfileImage(String username, MultipartFile newProfileImage) throws IOException;
}
