/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.enumeration;

public enum LessonLevel {
    A1("Low Beginner - A1"),
    A2("High Beginner - A2"),
    B1("Low Intermediate - B1"),
    B2("High Intermediate - B2"),
    C1("Low Advanced - C1"),
    C2("High Advanced - C2");

    public final String label;

    LessonLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
