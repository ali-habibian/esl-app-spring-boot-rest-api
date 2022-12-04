package com.habibian.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    // that's an actual id to show it to the customers.
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String profileImageUrl;
    private Date lastLoginDate;
    private Date lastLoginDateDisplay;
    private Date joinDate;
    private String role;
    private String[] authorities;
    private boolean isActive;
    private boolean isNotLocked;
    private boolean isSubscribed;
}
