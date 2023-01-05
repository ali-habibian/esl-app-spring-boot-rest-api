package com.habibian.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserCreationRequest {
    @NotBlank(message = "First name is mandatory")
    @JsonProperty("firstName")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @JsonProperty("lastName")
    private String lastName;

    @NotBlank(message = "Username is mandatory")
    @JsonProperty("username")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @JsonProperty("password")
    private String password;

    @Email
    @NotBlank(message = "Email is mandatory")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "Role is mandatory")
    @JsonProperty("role")
    private String role;

    @NotBlank(message = "Phone number is mandatory")
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @NotBlank(message = "IsNonLocked is mandatory")
    @JsonProperty("isNonLocked")
    private boolean isNonLocked;

    @NotBlank(message = "IsActive is mandatory")
    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("profileImage")
    private MultipartFile profileImage;
}
