/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.repository;

import com.habibian.domain.entity.GapFillQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The GapFillQuizRepository interface provides database operations for GapFillQuiz entities.
 * It extends the JpaRepository interface, which provides basic CRUD operations.
 */
public interface GapFillQuizRepository extends JpaRepository<GapFillQuiz, Long> {

    /**
     * Retrieves a list of gap fill quizzes by the specified listening lesson ID.
     *
     * @param lessonId The ID of the listening lesson
     * @return A list of GapFillQuiz objects associated with the specified listening lesson
     */
    List<GapFillQuiz> findAllByListeningLesson_Id(long lessonId);

    /**
     * Retrieves a page of gap fill quizzes by the specified listening lesson ID.
     *
     * @param lessonId The ID of the listening lesson
     * @param pageable The pagination information
     * @return A page of GapFillQuiz objects associated with the specified listening lesson
     */
    Page<GapFillQuiz> findAllByListeningLesson_Id(long lessonId, Pageable pageable);
}
