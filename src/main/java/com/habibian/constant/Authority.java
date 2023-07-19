/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.constant;

public class Authority {

    // Authority array for regular users
    public static final String[] USER_AUTHORITIES = {"user:read"};

    // Authority array for admin users
    public static final String[] ADMIN_AUTHORITIES = {"user:read", "user:create", "user:update"};

    // Authority array for super admin users
    public static final String[] SUPER_ADMIN_AUTHORITIES = {"user:read", "user:create", "user:update", "user:delete"};
}
