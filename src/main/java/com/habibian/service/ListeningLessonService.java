/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service;

import com.habibian.dto.listening_lesson.ListeningLessonRequestDTO;
import com.habibian.dto.listening_lesson.ListeningLessonResponseDTO;
import com.habibian.enumeration.LessonLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * The ListeningLessonService interface provides methods to create, retrieve, update, and delete listening lessons.
 */
public interface ListeningLessonService {

    /**
     * Creates a new listening lesson based on the provided request, audio file URL, and script file URL.
     *
     * @param request       The ListeningLessonRequestDTO object containing the details of the listening lesson.
     * @param audioFileUrl  The URL of the audio file associated with the listening lesson.
     * @param scriptFileUrl The URL of the script file associated with the listening lesson.
     * @return The ListeningLessonResponseDTO object representing the created listening lesson.
     */
    ListeningLessonResponseDTO create(ListeningLessonRequestDTO request, String audioFileUrl, String scriptFileUrl);

    /**
     * Retrieves a listening lesson by its ID.
     *
     * @param id The ID of the listening lesson to retrieve.
     * @return The ListeningLessonResponseDTO object representing the retrieved listening lesson.
     */
    ListeningLessonResponseDTO getById(long id);

    /**
     * Retrieves all listening lessons.
     *
     * @return A list of ListeningLessonResponseDTO objects representing the listening lessons.
     */
    List<ListeningLessonResponseDTO> getAll();

    /**
     * Retrieves a page of listening lessons using pagination.
     *
     * @param pageable The Pageable object specifying the page size and page number.
     * @return A Page object containing the listening lessons for the specified page.
     */
    Page<ListeningLessonResponseDTO> getAllWithPagination(Pageable pageable);

    /**
     * Retrieves all listening lessons of a specific level.
     *
     * @param level The LessonLevel enum representing the level of the listening lessons.
     * @return A list of ListeningLessonResponseDTO objects representing the listening lessons of the specified level.
     */
    List<ListeningLessonResponseDTO> getAllByLevel(LessonLevel level);

    /**
     * Retrieves a page of listening lessons of a specific level using pagination.
     *
     * @param level    The LessonLevel enum representing the level of the listening lessons.
     * @param pageable The Pageable object specifying the page size and page number.
     * @return A Page object containing the listening lessons of the specified level for the specified page.
     */
    Page<ListeningLessonResponseDTO> getAllByLevelWithPagination(LessonLevel level, Pageable pageable);

    /**
     * Updates a listening lesson with the provided request, audio file URL, and script file URL.
     *
     * @param id            The ID of the listening lesson to update.
     * @param request       The ListeningLessonRequestDTO object containing the updated details of the listening lesson.
     * @param audioFileUrl  The updated URL of the audio file associated with the listening lesson.
     * @param scriptFileUrl The updated URL of the script file associated with the listening lesson.
     * @return The ListeningLessonResponseDTO object representing the updated listening lesson.
     */
    ListeningLessonResponseDTO update(long id, ListeningLessonRequestDTO request, String audioFileUrl, String scriptFileUrl);

    /**
     * Deletes a listening lesson by its ID.
     *
     * @param id The ID of the listening lesson to delete.
     */
    void delete(long id);
}
