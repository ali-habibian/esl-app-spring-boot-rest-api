/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.repository;

import com.habibian.domain.entity.ListeningLesson;
import com.habibian.enumeration.LessonLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link ListeningLesson} entities.
 * Provides methods to perform CRUD operations and retrieve listening lessons by level.
 */
public interface ListeningLessonRepository extends JpaRepository<ListeningLesson, Long> {

    /**
     * Retrieve all listening lessons with a specific level.
     *
     * @param level The level of the listening lessons
     * @return A list of {@link ListeningLesson} objects with the specified level
     */
    List<ListeningLesson> findAllByLevel(LessonLevel level);

    /**
     * Retrieve a page of listening lessons with a specific level.
     *
     * @param level    The level of the listening lessons
     * @param pageable The pagination information
     * @return A {@link Page} of {@link ListeningLesson} objects with the specified level
     */
    Page<ListeningLesson> findAllByLevel(LessonLevel level, Pageable pageable);
}

