package com.habibian.service;

import com.habibian.dto.multiple_choice_quiz.MultipleChoiceQuizRequestDTO;
import com.habibian.dto.multiple_choice_quiz.MultipleChoiceQuizResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MultipleChoiceQuizService {
    MultipleChoiceQuizResponseDTO create(MultipleChoiceQuizRequestDTO requestDTO);

    MultipleChoiceQuizResponseDTO getById(long id);

    List<MultipleChoiceQuizResponseDTO> getAllByLessonId(long lessonId);

    Page<MultipleChoiceQuizResponseDTO> getAllByLessonId(long lessonId, Pageable pageable);

    MultipleChoiceQuizResponseDTO update(long id, MultipleChoiceQuizRequestDTO requestDTO);

    void delete(long id);
}
