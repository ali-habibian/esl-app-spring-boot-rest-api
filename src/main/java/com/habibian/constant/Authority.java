/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.constant;

public class Authority {

    // Authority array for regular users
    public static final String[] USER_AUTHORITIES = {
            "role:read"
    };

    // Authority array for admin users
    public static final String[] ADMIN_AUTHORITIES = {
            "role:read", "role:create", "role:update", "role:delete",
    };

    // Authority array for super admin users
    public static final String[] SUPER_ADMIN_AUTHORITIES = {
            "role:read", "role:create", "role:update", "role:delete", "role:s-admin"
    };
}
