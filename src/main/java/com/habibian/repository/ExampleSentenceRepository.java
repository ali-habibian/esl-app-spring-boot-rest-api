/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.repository;

import com.habibian.domain.entity.ExampleSentence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The ExampleSentenceRepository interface provides database operations for ExampleSentence entities.
 * It extends the JpaRepository interface, which provides basic CRUD operations.
 */
public interface ExampleSentenceRepository extends JpaRepository<ExampleSentence, Long> {

    /**
     * Retrieves a list of example sentences by the specified vocabulary ID.
     *
     * @param vocabId The ID of the vocabulary
     * @return A list of ExampleSentence objects associated with the specified vocabulary
     */
    List<ExampleSentence> findAllByVocab_Id(Long vocabId);
}
