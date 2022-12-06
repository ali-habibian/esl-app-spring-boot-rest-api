package com.habibian.service;

import com.habibian.dto.user.UserCreationDTO;
import com.habibian.dto.user.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO registerUser(UserCreationDTO userCreationDTO);

    List<UserDTO> getUsers();

    UserDTO findUserByUsername(String username);

    UserDTO findUserByEmail(String email);
}
