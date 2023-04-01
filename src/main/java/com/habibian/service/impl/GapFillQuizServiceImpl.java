/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service.impl;

import com.habibian.domain.entity.GapFillQuiz;
import com.habibian.domain.entity.ListeningLesson;
import com.habibian.dto.gap_fill_quiz.GapFillQuizRequestDTO;
import com.habibian.dto.gap_fill_quiz.GapFillQuizResponseDTO;
import com.habibian.dto.listening_lesson.ListeningLessonResponseDTO;
import com.habibian.exception.domain.ResourceNotFoundException;
import com.habibian.repository.GapFillQuizRepository;
import com.habibian.service.GapFillQuizService;
import com.habibian.service.ListeningLessonService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GapFillQuizServiceImpl implements GapFillQuizService {
    private final GapFillQuizRepository gapFillQuizRepository;
    private final ListeningLessonService lessonService;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    @Override
    public GapFillQuizResponseDTO create(GapFillQuizRequestDTO requestDTO) {
        GapFillQuiz gapFillQuiz = new GapFillQuiz();

        return getGapFillQuizResponseDTO(requestDTO, gapFillQuiz);
    }

    @Override
    public GapFillQuizResponseDTO getById(long id) {
        GapFillQuiz gapFillQuiz = gapFillQuizRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("GapFillQuiz", "ID", id));
        return modelMapper.map(gapFillQuiz, GapFillQuizResponseDTO.class);
    }

    @Override
    public List<GapFillQuizResponseDTO> getAllByLessonId(long lessonId) {
        List<GapFillQuiz> quizList = gapFillQuizRepository.findAllByListeningLesson_Id(lessonId);
        return quizList.stream()
                .map(gapFillQuiz -> modelMapper.map(gapFillQuiz, GapFillQuizResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<GapFillQuizResponseDTO> getAllByLessonId(long lessonId, Pageable pageable) {
        Page<GapFillQuiz> quizPage = gapFillQuizRepository.findAllByListeningLesson_Id(lessonId, pageable);
        return quizPage.map(gapFillQuiz -> modelMapper.map(gapFillQuiz, GapFillQuizResponseDTO.class));
    }

    @Override
    public GapFillQuizResponseDTO update(long id, GapFillQuizRequestDTO requestDTO) {
        GapFillQuiz gapFillQuiz = gapFillQuizRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("GapFillQuiz", "ID", id)
        );

        return getGapFillQuizResponseDTO(requestDTO, gapFillQuiz);
    }

    @Override
    public void delete(long id) {
        GapFillQuiz gapFillQuiz = gapFillQuizRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("GapFillQuiz", "ID", id)
        );

        gapFillQuizRepository.delete(gapFillQuiz);
    }

    private GapFillQuizResponseDTO getGapFillQuizResponseDTO(GapFillQuizRequestDTO requestDTO, GapFillQuiz gapFillQuiz) {
        ListeningLessonResponseDTO lessonResponseDTO = lessonService.getById(requestDTO.getLessonId());
        ListeningLesson listeningLesson = entityManager.merge(modelMapper.map(lessonResponseDTO, ListeningLesson.class));

        gapFillQuiz.setQuestion(requestDTO.getQuestion());
        gapFillQuiz.setAnswer(requestDTO.getAnswer());
        gapFillQuiz.setListeningLesson(listeningLesson);

        listeningLesson.addGapFillQuiz(gapFillQuiz);

        GapFillQuiz response = gapFillQuizRepository.save(gapFillQuiz);
        gapFillQuizRepository.flush();

        return modelMapper.map(response, GapFillQuizResponseDTO.class);
    }
}
