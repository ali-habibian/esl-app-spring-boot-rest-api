package com.habibian.service;

import com.habibian.domain.entity.User;
import com.habibian.dto.user.UserCreationDTO;
import com.habibian.dto.user.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO registerUser(UserCreationDTO userCreationDTO);

    List<UserDTO> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
