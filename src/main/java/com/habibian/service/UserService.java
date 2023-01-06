package com.habibian.service;

import com.habibian.domain.entity.User;
import com.habibian.dto.user.UserCreationRequest;
import com.habibian.dto.user.UserRegisterRequest;
import com.habibian.dto.user.UserDTO;
import com.habibian.dto.user.UserUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    UserDTO registerUser(UserRegisterRequest userRegisterRequest);

    List<UserDTO> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    UserDTO addNewUser(UserCreationRequest request) throws IOException;

    UserDTO updateUser(UserUpdateRequest request) throws IOException;

    void deleteUser(String username) throws IOException;

    void resetPassword(String email);

    UserDTO updateProfileImage(String username, MultipartFile newProfileImage) throws IOException;
}
