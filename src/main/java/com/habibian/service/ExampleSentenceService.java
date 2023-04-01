/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service;

import com.habibian.dto.example_sentence.ExampleSentenceRequestDTO;
import com.habibian.dto.example_sentence.ExampleSentenceResponseDTO;

import java.util.List;

public interface ExampleSentenceService {
    ExampleSentenceResponseDTO create(ExampleSentenceRequestDTO requestDTO);

    ExampleSentenceResponseDTO getById(long id);

    List<ExampleSentenceResponseDTO> getAllByVocabId(long vocabId);

    ExampleSentenceResponseDTO update(long id, ExampleSentenceRequestDTO requestDTO);

    void delete(long id);
}
