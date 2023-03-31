package com.habibian.service;

import com.habibian.dto.gap_fill_quiz.GapFillQuizRequestDTO;
import com.habibian.dto.gap_fill_quiz.GapFillQuizResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GapFillQuizService {
    GapFillQuizResponseDTO create(GapFillQuizRequestDTO requestDTO);

    GapFillQuizResponseDTO getById(long id);

    List<GapFillQuizResponseDTO> getAllByLessonId(long lessonId);

    Page<GapFillQuizResponseDTO> getAllByLessonId(long lessonId, Pageable pageable);

    GapFillQuizResponseDTO update(long id, GapFillQuizRequestDTO requestDTO);

    void delete(long id);
}
