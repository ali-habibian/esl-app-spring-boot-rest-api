package com.habibian.controller;

import com.habibian.dto.user.UserCreationDTO;
import com.habibian.dto.user.UserDTO;
import com.habibian.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        UserDTO userDTO = userService.registerUser(userCreationDTO);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
