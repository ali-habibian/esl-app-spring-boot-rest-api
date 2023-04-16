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

public interface VocabService {
    VocabResponseDTO create(VocabRequestDTO requestDTO);

    VocabResponseDTO getById(long id);

    List<VocabResponseDTO> getAll();

    Page<VocabResponseDTO> getAllWithPagination(Pageable pageable);

    List<VocabResponseDTO> getAllByLessonId(long lessonId);

    // TODO - add method getAllByLessonId with pagination

    VocabResponseDTO update(long id, VocabRequestDTO requestDTO);

    void delete(long id);
}
