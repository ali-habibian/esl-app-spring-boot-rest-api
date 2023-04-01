/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.repository;

import com.habibian.domain.entity.ExampleSentence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExampleSentenceRepository extends JpaRepository<ExampleSentence, Long> {
    List<ExampleSentence> findAllByVocab_Id(Long vocabId);
}
