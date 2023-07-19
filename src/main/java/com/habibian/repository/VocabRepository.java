/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.repository;

import com.habibian.domain.entity.Vocab;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing Vocab entities.
 */
public interface VocabRepository extends JpaRepository<Vocab, Long> {

    /**
     * Find all vocabs associated with a specific listening lesson.
     *
     * @param lessonId The ID of the listening lesson
     * @return A list of Vocab objects associated with the listening lesson
     */
    List<Vocab> findAllByListeningLesson_Id(long lessonId);

    /**
     * Find all vocabs associated with a specific listening lesson, with pagination.
     *
     * @param lessonId The ID of the listening lesson
     * @param pageable The pagination information
     * @return A Page object containing the paginated list of Vocab objects associated with the listening lesson
     */
    Page<Vocab> findAllByListeningLesson_Id(long lessonId, Pageable pageable);
}
