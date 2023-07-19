/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service;

import com.habibian.dto.example_sentence.ExampleSentenceRequestDTO;
import com.habibian.dto.example_sentence.ExampleSentenceResponseDTO;

import java.util.List;

/**
 * The ExampleSentenceService interface provides methods to create, retrieve, update, and delete example sentences.
 */
public interface ExampleSentenceService {

    /**
     * Creates a new example sentence based on the provided requestDTO.
     *
     * @param requestDTO The ExampleSentenceRequestDTO object containing the details of the example sentence.
     * @return The ExampleSentenceResponseDTO object representing the created example sentence.
     */
    ExampleSentenceResponseDTO create(ExampleSentenceRequestDTO requestDTO);

    /**
     * Retrieves an example sentence by its ID.
     *
     * @param id The ID of the example sentence to retrieve.
     * @return The ExampleSentenceResponseDTO object representing the retrieved example sentence.
     */
    ExampleSentenceResponseDTO getById(long id);

    /**
     * Retrieves all example sentences associated with a specific vocabulary ID.
     *
     * @param vocabId The ID of the vocabulary to retrieve example sentences for.
     * @return A list of ExampleSentenceResponseDTO objects representing the example sentences.
     */
    List<ExampleSentenceResponseDTO> getAllByVocabId(long vocabId);

    /**
     * Updates an example sentence with the provided requestDTO.
     *
     * @param id         The ID of the example sentence to update.
     * @param requestDTO The ExampleSentenceRequestDTO object containing the updated details of the example sentence.
     * @return The ExampleSentenceResponseDTO object representing the updated example sentence.
     */
    ExampleSentenceResponseDTO update(long id, ExampleSentenceRequestDTO requestDTO);

    /**
     * Deletes an example sentence by its ID.
     *
     * @param id The ID of the example sentence to delete.
     */
    void delete(long id);
}
