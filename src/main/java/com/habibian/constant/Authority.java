/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.constant;

public class Authority {

    // Authority array for regular users
    public static final String[] USER_AUTHORITIES = {
            "user:read-self", "user:update-self",
            "listeningLesson:read",
            "vocab:read",
            "exampleSentence:read",
            "gapFillQuiz:read",
            "multipleChoiceQuiz:read"
    };

    // Authority array for admin users
    public static final String[] ADMIN_AUTHORITIES = {
            "user:read", "user:create", "user:update",
            "listeningLesson:read", "listeningLesson:create", "listeningLesson:update", "listeningLesson:delete",
            "vocab:read", "vocab:create", "vocab:update", "vocab:delete",
            "exampleSentence:read", "exampleSentence:create", "exampleSentence:update", "exampleSentence:delete",
            "gapFillQuiz:read", "gapFillQuiz:create", "gapFillQuiz:update", "gapFillQuiz:delete",
            "multipleChoiceQuiz:read", "multipleChoiceQuiz:create", "multipleChoiceQuiz:update", "multipleChoiceQuiz:delete"
    };

    // Authority array for super admin users
    public static final String[] SUPER_ADMIN_AUTHORITIES = {
            "user:read", "user:create", "user:update", "user:delete",
            "listeningLesson:read", "listeningLesson:create", "listeningLesson:update", "listeningLesson:delete",
            "vocab:read", "vocab:create", "vocab:update", "vocab:delete",
            "exampleSentence:read", "exampleSentence:create", "exampleSentence:update", "exampleSentence:delete",
            "gapFillQuiz:read", "gapFillQuiz:create", "gapFillQuiz:update", "gapFillQuiz:delete",
            "multipleChoiceQuiz:read", "multipleChoiceQuiz:create", "multipleChoiceQuiz:update", "multipleChoiceQuiz:delete"
    };
}
