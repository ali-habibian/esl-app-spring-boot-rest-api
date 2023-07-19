/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service;

import com.habibian.dto.multiple_choice_quiz.MultipleChoiceQuizRequestDTO;
import com.habibian.dto.multiple_choice_quiz.MultipleChoiceQuizResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * The MultipleChoiceQuizService interface provides methods to create, retrieve, update, and delete multiple-choice quizzes.
 */
public interface MultipleChoiceQuizService {

    /**
     * Creates a new multiple-choice quiz based on the provided requestDTO.
     *
     * @param requestDTO The MultipleChoiceQuizRequestDTO object containing the details of the multiple-choice quiz.
     * @return The MultipleChoiceQuizResponseDTO object representing the created multiple-choice quiz.
     */
    MultipleChoiceQuizResponseDTO create(MultipleChoiceQuizRequestDTO requestDTO);

    /**
     * Retrieves a multiple-choice quiz by its ID.
     *
     * @param id The ID of the multiple-choice quiz to retrieve.
     * @return The MultipleChoiceQuizResponseDTO object representing the retrieved multiple-choice quiz.
     */
    MultipleChoiceQuizResponseDTO getById(long id);

    /**
     * Retrieves all multiple-choice quizzes associated with a specific lesson ID.
     *
     * @param lessonId The ID of the lesson to retrieve multiple-choice quizzes for.
     * @return A list of MultipleChoiceQuizResponseDTO objects representing the multiple-choice quizzes.
     */
    List<MultipleChoiceQuizResponseDTO> getAllByLessonId(long lessonId);

    /**
     * Retrieves a page of multiple-choice quizzes associated with a specific lesson ID, using pagination.
     *
     * @param lessonId The ID of the lesson to retrieve multiple-choice quizzes for.
     * @param pageable The Pageable object specifying the page size and page number.
     * @return A Page object containing the multiple-choice quizzes for the specified page.
     */
    Page<MultipleChoiceQuizResponseDTO> getAllByLessonId(long lessonId, Pageable pageable);

    /**
     * Updates a multiple-choice quiz with the provided requestDTO.
     *
     * @param id         The ID of the multiple-choice quiz to update.
     * @param requestDTO The MultipleChoiceQuizRequestDTO object containing the updated details of the multiple-choice quiz.
     * @return The MultipleChoiceQuizResponseDTO object representing the updated multiple-choice quiz.
     */
    MultipleChoiceQuizResponseDTO update(long id, MultipleChoiceQuizRequestDTO requestDTO);

    /**
     * Deletes a multiple-choice quiz by its ID.
     *
     * @param id The ID of the multiple-choice quiz to delete.
     */
    void delete(long id);
}
