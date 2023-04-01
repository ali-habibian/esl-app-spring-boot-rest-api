/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.exception.domain;

public class NotAnImageFileException extends RuntimeException {
    public NotAnImageFileException(String message) {
        super(message);
    }
}
