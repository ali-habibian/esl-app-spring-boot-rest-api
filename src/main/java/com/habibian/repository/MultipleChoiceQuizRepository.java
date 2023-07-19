/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.repository;

import com.habibian.domain.entity.MultipleChoiceQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing MultipleChoiceQuiz entities.
 */
public interface MultipleChoiceQuizRepository extends JpaRepository<MultipleChoiceQuiz, Long> {

    /**
     * Retrieve all multiple-choice quizzes associated with a specific listening lesson.
     *
     * @param lessonId The ID of the listening lesson
     * @return A list of MultipleChoiceQuiz objects associated with the listening lesson
     */
    List<MultipleChoiceQuiz> findAllByListeningLesson_Id(long lessonId);

    /**
     * Retrieve a page of multiple-choice quizzes associated with a specific listening lesson.
     *
     * @param lessonId The ID of the listening lesson
     * @param pageable The pagination information
     * @return A page of MultipleChoiceQuiz objects associated with the listening lesson
     */
    Page<MultipleChoiceQuiz> findAllByListeningLesson_Id(long lessonId, Pageable pageable);
}
