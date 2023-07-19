/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service;

import com.habibian.dto.vocab.VocabRequestDTO;
import com.habibian.dto.vocab.VocabResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * The VocabService interface provides methods to create, retrieve, update, and delete vocabulary entries.
 */
public interface VocabService {

    /**
     * Creates a new vocabulary entry based on the provided requestDTO.
     *
     * @param requestDTO The VocabRequestDTO object containing the details of the vocabulary entry.
     * @return The VocabResponseDTO object representing the created vocabulary entry.
     */
    VocabResponseDTO create(VocabRequestDTO requestDTO);

    /**
     * Retrieves a vocabulary entry by its ID.
     *
     * @param id The ID of the vocabulary entry to retrieve.
     * @return The VocabResponseDTO object representing the retrieved vocabulary entry.
     */
    VocabResponseDTO getById(long id);

    /**
     * Retrieves all vocabulary entries.
     *
     * @return A list of VocabResponseDTO objects representing the vocabulary entries.
     */
    List<VocabResponseDTO> getAll();

    /**
     * Retrieves a page of vocabulary entries using pagination.
     *
     * @param pageable The Pageable object specifying the page size and page number.
     * @return A Page object containing the vocabulary entries for the specified page.
     */
    Page<VocabResponseDTO> getAllWithPagination(Pageable pageable);

    /**
     * Retrieves all vocabulary entries associated with a specific lesson ID.
     *
     * @param lessonId The ID of the lesson to retrieve vocabulary entries for.
     * @return A list of VocabResponseDTO objects representing the vocabulary entries.
     */
    List<VocabResponseDTO> getAllByLessonId(long lessonId);

    /**
     * Retrieves a page of vocabulary entries associated with a specific lesson ID using pagination.
     *
     * @param lessonId The ID of the lesson to retrieve vocabulary entries for.
     * @param pageable The Pageable object specifying the page size and page number.
     * @return A Page object containing the vocabulary entries for the specified page.
     */
    Page<VocabResponseDTO> getAllByLessonIdWithPagination(long lessonId, Pageable pageable);

    /**
     * Updates a vocabulary entry with the provided requestDTO.
     *
     * @param id         The ID of the vocabulary entry to update.
     * @param requestDTO The VocabRequestDTO object containing the updated details of the vocabulary entry.
     * @return The VocabResponseDTO object representing the updated vocabulary entry.
     */
    VocabResponseDTO update(long id, VocabRequestDTO requestDTO);

    /**
     * Deletes a vocabulary entry by its ID.
     *
     * @param id The ID of the vocabulary entry to delete.
     */
    void delete(long id);
}
