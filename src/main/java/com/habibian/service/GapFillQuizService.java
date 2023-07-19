/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service;

import com.habibian.dto.gap_fill_quiz.GapFillQuizRequestDTO;
import com.habibian.dto.gap_fill_quiz.GapFillQuizResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * The GapFillQuizService interface provides methods to create, retrieve, update, and delete gap fill quizzes.
 */
public interface GapFillQuizService {

    /**
     * Creates a new gap fill quiz based on the provided requestDTO.
     *
     * @param requestDTO The GapFillQuizRequestDTO object containing the details of the gap fill quiz.
     * @return The GapFillQuizResponseDTO object representing the created gap fill quiz.
     */
    GapFillQuizResponseDTO create(GapFillQuizRequestDTO requestDTO);

    /**
     * Retrieves a gap fill quiz by its ID.
     *
     * @param id The ID of the gap fill quiz to retrieve.
     * @return The GapFillQuizResponseDTO object representing the retrieved gap fill quiz.
     */
    GapFillQuizResponseDTO getById(long id);

    /**
     * Retrieves all gap fill quizzes associated with a specific lesson ID.
     *
     * @param lessonId The ID of the lesson to retrieve gap fill quizzes for.
     * @return A list of GapFillQuizResponseDTO objects representing the gap fill quizzes.
     */
    List<GapFillQuizResponseDTO> getAllByLessonId(long lessonId);

    /**
     * Retrieves a page of gap fill quizzes associated with a specific lesson ID, using pagination.
     *
     * @param lessonId The ID of the lesson to retrieve gap fill quizzes for.
     * @param pageable The Pageable object specifying the page size and page number.
     * @return A Page object containing the gap fill quizzes for the specified page.
     */
    Page<GapFillQuizResponseDTO> getAllByLessonId(long lessonId, Pageable pageable);

    /**
     * Updates a gap fill quiz with the provided requestDTO.
     *
     * @param id         The ID of the gap fill quiz to update.
     * @param requestDTO The GapFillQuizRequestDTO object containing the updated details of the gap fill quiz.
     * @return The GapFillQuizResponseDTO object representing the updated gap fill quiz.
     */
    GapFillQuizResponseDTO update(long id, GapFillQuizRequestDTO requestDTO);

    /**
     * Deletes a gap fill quiz by its ID.
     *
     * @param id The ID of the gap fill quiz to delete.
     */
    void delete(long id);
}
