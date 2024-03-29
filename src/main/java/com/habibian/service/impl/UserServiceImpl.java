/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service.impl;

import com.habibian.domain.UserPrincipal;
import com.habibian.domain.entity.User;
import com.habibian.dto.user.UserCreationRequest;
import com.habibian.dto.user.UserRegisterRequest;
import com.habibian.dto.user.UserDTO;
import com.habibian.dto.user.UserUpdateRequest;
import com.habibian.enumeration.Role;
import com.habibian.exception.domain.EmailExistException;
import com.habibian.exception.domain.NotAnImageFileException;
import com.habibian.exception.domain.UserNotFoundException;
import com.habibian.exception.domain.UsernameExistException;
import com.habibian.repository.UserRepository;
import com.habibian.service.LoginAttemptService;
import com.habibian.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.habibian.constant.FileConstant.*;
import static com.habibian.constant.FileConstant.DEFAULT_USER_IMAGE_PATH;
import static com.habibian.constant.UserImplConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.http.MediaType.*;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;
    private final LoginAttemptService loginAttemptService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, LoginAttemptService loginAttemptService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.loginAttemptService = loginAttemptService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            LOGGER.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            validateLoginAttempt(user);

            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());

            userRepository.save(user);

            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info(FOUND_USER_BY_USERNAME + username);

            return userPrincipal;
        }
    }

    @Override
    public UserDTO registerUser(UserRegisterRequest userRegisterRequest) {
        validateNewUsernameAndEmail(EMPTY, userRegisterRequest.getUsername(), userRegisterRequest.getEmail());

        String encodedPassword = encodePassword(userRegisterRequest.getPassword());

        User user = new User();
        user.setUserId(generateUserId());
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setUsername(userRegisterRequest.getUsername());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPhoneNumber(userRegisterRequest.getPhoneNumber());
        user.setJoinDate(new Date());
        user.setPassword(encodedPassword);
        user.setActive(true);
        user.setNotLocked(true);
        user.setSubscribed(false);
        user.setRole(Role.ROLE_USER.name());
        user.setAuthorities(Role.ROLE_USER.getAuthorities());
        user.setProfileImageUrl(setProfileImageUrl(userRegisterRequest.getUsername()));

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map((user) ->
                modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDTO addNewUser(UserCreationRequest request) throws IOException {
        validateNewUsernameAndEmail(EMPTY, request.getUsername(), request.getEmail());

        User user = new User();
        user.setUserId(generateUserId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setJoinDate(new Date());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encodePassword(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setActive(Boolean.parseBoolean(request.getIsActive()));
        user.setNotLocked(Boolean.parseBoolean(request.getIsNonLocked()));
        user.setRole(getRoleEnumName(request.getRole()).name());
        user.setAuthorities(getRoleEnumName(request.getRole()).getAuthorities());
        user.setProfileImageUrl(getTemporaryProfileImageUrl(request.getUsername()));
        userRepository.save(user);
        saveProfileImage(user, request.getProfileImage());

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserUpdateRequest request) throws IOException {
        User currentUser = validateNewUsernameAndEmail(request.getCurrentUsername(), request.getNewUsername(), request.getNewEmail());

        assert currentUser != null;
        currentUser.setFirstName(request.getNewFirstName());
        currentUser.setLastName(request.getNewLastName());
        currentUser.setUsername(request.getNewUsername());
        currentUser.setEmail(request.getNewEmail());
        currentUser.setActive(Boolean.parseBoolean(request.getIsActive()));
        currentUser.setNotLocked(Boolean.parseBoolean(request.getIsNonLocked()));
        currentUser.setRole(getRoleEnumName(request.getRole()).name());
        currentUser.setAuthorities(getRoleEnumName(request.getRole()).getAuthorities());
        userRepository.save(currentUser);
        saveProfileImage(currentUser, request.getNewProfileImage());

        return modelMapper.map(currentUser, UserDTO.class);
    }

    @Override
    public void deleteUser(String username) throws IOException {
        User user = userRepository.findByUsername(username);
        Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
        FileUtils.deleteDirectory(new File(userFolder.toString()));
        userRepository.deleteById(user.getId());
    }

    @Override
    public void resetPassword(String email) {
        /* TODO
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + email);
        }
        String password = generatePassword();
        user.setPassword(encodePassword(password));
        userRepository.save(user);
        LOGGER.info("New user password: " + password);
        emailService.sendNewPasswordEmail(user.getFirstName(), password, user.getEmail());
        */
    }

    @Override
    public UserDTO updateProfileImage(String username, MultipartFile newProfileImage) throws IOException {
        User user = validateNewUsernameAndEmail(username, null, null);
        saveProfileImage(user, newProfileImage);
        return modelMapper.map(user, UserDTO.class);
    }

    private String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) {
        User userByNewUsername = userRepository.findByUsername(newUsername);
        User userByNewEmail = userRepository.findByEmail(newEmail);
        if (StringUtils.isNotBlank(currentUsername)) {
            User currentUser = userRepository.findByUsername(currentUsername);
            if (currentUser == null) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }
            if (userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if (userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return currentUser;
        } else {
            if (userByNewUsername != null) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if (userByNewEmail != null) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return null;
        }
    }

    private Role getRoleEnumName(String role) {
        return Role.valueOf(role.toUpperCase());
    }

    private String getTemporaryProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username).toUriString();
    }

    private void saveProfileImage(User user, MultipartFile profileImage) throws IOException, NotAnImageFileException {
        if (profileImage != null) {
            if (!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE).contains(profileImage.getContentType())) {
                throw new NotAnImageFileException(profileImage.getOriginalFilename() + NOT_AN_IMAGE_FILE);
            }
            Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
                LOGGER.info(DIRECTORY_CREATED + userFolder);
            }
            Files.deleteIfExists(Paths.get(userFolder + user.getUsername() + DOT + JPG_EXTENSION));
            Files.copy(profileImage.getInputStream(), userFolder.resolve(user.getUsername() + DOT + JPG_EXTENSION), REPLACE_EXISTING);
            user.setProfileImageUrl(setProfileImageUrl(user.getUsername()));
            userRepository.save(user);
            LOGGER.info(FILE_SAVED_IN_FILE_SYSTEM + profileImage.getOriginalFilename());
        }
    }

    private String setProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_IMAGE_PATH + username + FORWARD_SLASH
                + username + DOT + JPG_EXTENSION).toUriString();
    }

    private void validateLoginAttempt(User user) {
        if (user.isNotLocked()) {
            user.setNotLocked(!loginAttemptService.hasExceededMaxAttempts(user.getUsername()));
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }
}
