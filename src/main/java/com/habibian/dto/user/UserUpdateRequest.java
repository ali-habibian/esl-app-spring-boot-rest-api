/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserUpdateRequest {

    @NotBlank(message = "Current Username is mandatory")
    @JsonProperty("currentUsername")
    private String currentUsername;

    @NotBlank(message = "New first name is mandatory")
    @JsonProperty("newFirstName")
    private String newFirstName;

    @NotBlank(message = "New last name is mandatory")
    @JsonProperty("newLastName")
    private String newLastName;

    @NotBlank(message = "New Username is mandatory")
    @JsonProperty("newUsername")
    private String newUsername;

    @NotBlank(message = "New password is mandatory")
    @JsonProperty("newPassword")
    private String newPassword;

    @Email
    @NotBlank(message = "New email is mandatory")
    @JsonProperty("newEmail")
    private String newEmail;

    @NotBlank(message = "Role is mandatory")
    @JsonProperty("role")
    private String role; // TODO must be removed

    @NotBlank(message = "Phone number is mandatory")
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @NotBlank(message = "IsNonLocked is mandatory")
    @JsonProperty("isNonLocked")
    private String isNonLocked;

    @NotBlank(message = "IsActive is mandatory")
    @JsonProperty("isActive")
    private String isActive;

    @JsonProperty("newProfileImage")
    private MultipartFile newProfileImage;
}
