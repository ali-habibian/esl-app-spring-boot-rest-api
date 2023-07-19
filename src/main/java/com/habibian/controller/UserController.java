/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.controller;

import com.habibian.domain.HttpResponse;
import com.habibian.domain.UserPrincipal;
import com.habibian.domain.entity.User;
import com.habibian.dto.user.*;
import com.habibian.service.UserService;
import com.habibian.utility.JwtTokenProvider;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.habibian.constant.FileConstant.*;
import static com.habibian.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping("/user")
public class UserController {

    public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Handles the POST request to log in a user.
     *
     * @param user The UserLoginRequest object containing the user's login credentials.
     * @return ResponseEntity with the UserDTO and JWT token in the headers, and HttpStatus.OK.
     */
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserLoginRequest user) {
        authenticate(user.getUsername(), user.getPassword());

        User loginUser = userService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);

        return new ResponseEntity<>(modelMapper.map(loginUser, UserDTO.class), jwtHeader, HttpStatus.OK);
    }

    /**
     * Handles the POST request to register a new user.
     *
     * @param userRegisterRequest The UserRegisterRequest object containing the user's registration data.
     * @return ResponseEntity with the UserDTO and HttpStatus.OK.
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        UserDTO userDTO = userService.registerUser(userRegisterRequest);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    /**
     * Handles the POST request to add a new user.
     *
     * @param request The UserCreationRequest object containing the user's data.
     * @return ResponseEntity with the UserDTO and HttpStatus.OK.
     * @throws IOException if an I/O error occurs.
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<UserDTO> addNewUser(@Valid @ModelAttribute UserCreationRequest request) throws IOException {
        UserDTO userDTO = userService.addNewUser(request);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    /**
     * Handles the POST request to update a user's information.
     *
     * @param request The UserUpdateRequest object containing the user's updated data.
     * @param jwt     The JWT token extracted from the "Authorization" header.
     * @return ResponseEntity with the UserDTO and HttpStatus.OK.
     * @throws IOException if an I/O error occurs.
     */
    @PostMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@Valid @ModelAttribute UserUpdateRequest request,
                                              @RequestHeader("Authorization") String jwt) throws IOException {

        String token = jwt.substring(7);
        String username = jwtTokenProvider.getSubject(token); // TODO extract CurrentUsername from request dto
        request.setCurrentUsername(username);
        UserDTO userDTO = userService.updateUser(request);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // TODO updateUserFromSuperAdmin

    /**
     * Handles the GET request to retrieve a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return ResponseEntity with the UserDTO and HttpStatus.OK.
     */
    @GetMapping("/find/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    /**
     * Handles the GET request to retrieve all users.
     *
     * @return ResponseEntity with a list of UserDTOs and HttpStatus.OK.
     */
    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Handles the DELETE request to delete a user by their username.
     *
     * @param username The username of the user to delete.
     * @return ResponseEntity with HttpStatus.NO_CONTENT.
     * @throws IOException if an I/O error occurs.
     */
    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<HttpResponse> deleteUserByUsername(@PathVariable String username) throws IOException {
        userService.deleteUser(username);
        return response(HttpStatus.NO_CONTENT, USER_DELETED_SUCCESSFULLY);
    }

    /**
     * Handles the POST request to update a user's profile image.
     *
     * @param username     The username of the user to update the profile image.
     * @param profileImage The MultipartFile containing the user's profile image.
     * @return ResponseEntity with the UserDTO and HttpStatus.OK.
     * @throws IOException if an I/O error occurs.
     */
    @PostMapping("/updateProfileImage") // TODO get username from jwt token
    public ResponseEntity<UserDTO> updateProfileImage(@RequestParam("username") String username,
                                                      @RequestParam(value = "profileImage") MultipartFile profileImage) throws IOException {
        UserDTO userDTO = userService.updateProfileImage(username, profileImage);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    /**
     * Handles the GET request to retrieve a user's profile image.
     *
     * @param username The username of the user.
     * @param filename The filename of the profile image.
     * @return The byte array representing the profile image.
     * @throws IOException if an I/O error occurs.
     */
    @GetMapping(path = "/image/{username}/{filename}", produces = IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable("username") String username,
                                  @PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(USER_FOLDER + username + FORWARD_SLASH + filename));
    }

    /**
     * Handles the GET request to retrieve a temporary profile image for a user.
     *
     * @param username The username of the user.
     * @return The byte array representing the temporary profile image.
     * @throws IOException if an I/O error occurs.
     */
    @GetMapping(path = "/image/profile/{username}", produces = IMAGE_JPEG_VALUE)
    public byte[] getTempProfileImage(@PathVariable("username") String username) throws IOException {
        URL url = new URL(TEMP_PROFILE_IMAGE_BASE_URL + username);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (InputStream inputStream = url.openStream()) {
            int bytesRead;
            byte[] chunk = new byte[1024];
            while ((bytesRead = inputStream.read(chunk)) > 0) {
                outputStream.write(chunk, 0, bytesRead);
            }
        }

        return outputStream.toByteArray();
    }

    /**
     * Creates an HTTP response with the provided status, message, and headers.
     *
     * @param httpStatus The HTTP status code.
     * @param message    The response message.
     * @return ResponseEntity with the HttpResponse and HttpStatus.
     */
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message),
                httpStatus);
    }

    /**
     * Generates the HttpHeaders with the JWT token.
     *
     * @param userPrincipal The UserPrincipal object containing user details.
     * @return HttpHeaders with the JWT token.
     */
    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));
        return httpHeaders;
    }

    /**
     * Authenticates the user using the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
