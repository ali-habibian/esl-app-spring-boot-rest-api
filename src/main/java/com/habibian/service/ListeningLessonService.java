package com.habibian.service;

import com.habibian.dto.listening_lesson.ListeningLessonRequestDTO;
import com.habibian.dto.listening_lesson.ListeningLessonResponseDTO;
import com.habibian.enumeration.LessonLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListeningLessonService {

    ListeningLessonResponseDTO create(ListeningLessonRequestDTO request);

    ListeningLessonResponseDTO getById(long id);

    List<ListeningLessonResponseDTO> getAll();

    Page<ListeningLessonResponseDTO> getAllWithPagination(Pageable pageable);

    List<ListeningLessonResponseDTO> getAllByLevel(LessonLevel level);

    Page<ListeningLessonResponseDTO> getAllByLevelWithPagination(LessonLevel level, Pageable pageable);

    ListeningLessonResponseDTO update(long id, ListeningLessonRequestDTO request);

    void delete(long id);
}
