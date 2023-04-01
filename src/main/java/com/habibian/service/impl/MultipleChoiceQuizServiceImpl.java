/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service.impl;

import com.habibian.domain.entity.ListeningLesson;
import com.habibian.domain.entity.MultipleChoiceQuiz;
import com.habibian.dto.listening_lesson.ListeningLessonResponseDTO;
import com.habibian.dto.multiple_choice_quiz.MultipleChoiceQuizRequestDTO;
import com.habibian.dto.multiple_choice_quiz.MultipleChoiceQuizResponseDTO;
import com.habibian.exception.domain.ResourceNotFoundException;
import com.habibian.repository.MultipleChoiceQuizRepository;
import com.habibian.service.ListeningLessonService;
import com.habibian.service.MultipleChoiceQuizService;
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
public class MultipleChoiceQuizServiceImpl implements MultipleChoiceQuizService {
    private final MultipleChoiceQuizRepository choiceQuizRepository;
    private final ListeningLessonService lessonService;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    @Override
    public MultipleChoiceQuizResponseDTO create(MultipleChoiceQuizRequestDTO requestDTO) {
        MultipleChoiceQuiz multipleChoiceQuiz = new MultipleChoiceQuiz();
        return getMultipleChoiceQuizResponseDTO(multipleChoiceQuiz, requestDTO);
    }

    @Override
    public MultipleChoiceQuizResponseDTO getById(long id) {
        MultipleChoiceQuiz multipleChoiceQuiz = choiceQuizRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("MultipleChoiceQuiz", "ID", id)
        );
        return modelMapper.map(multipleChoiceQuiz, MultipleChoiceQuizResponseDTO.class);
    }

    @Override
    public List<MultipleChoiceQuizResponseDTO> getAllByLessonId(long lessonId) {
        List<MultipleChoiceQuiz> quizList = choiceQuizRepository.findAllByListeningLesson_Id(lessonId);
        return quizList.stream()
                .map(multipleChoiceQuiz -> modelMapper.map(multipleChoiceQuiz, MultipleChoiceQuizResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<MultipleChoiceQuizResponseDTO> getAllByLessonId(long lessonId, Pageable pageable) {
        Page<MultipleChoiceQuiz> quizPage = choiceQuizRepository.findAllByListeningLesson_Id(lessonId, pageable);
        return quizPage.map(multipleChoiceQuiz ->
                modelMapper.map(multipleChoiceQuiz, MultipleChoiceQuizResponseDTO.class));
    }

    @Override
    public MultipleChoiceQuizResponseDTO update(long id, MultipleChoiceQuizRequestDTO requestDTO) {
        MultipleChoiceQuiz multipleChoiceQuiz = choiceQuizRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("MultipleChoiceQuiz", "ID", id)
        );

        return getMultipleChoiceQuizResponseDTO(multipleChoiceQuiz, requestDTO);
    }

    @Override
    public void delete(long id) {
        MultipleChoiceQuiz multipleChoiceQuiz = choiceQuizRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("MultipleChoiceQuiz", "ID", id)
        );

        choiceQuizRepository.delete(multipleChoiceQuiz);
    }

    private MultipleChoiceQuizResponseDTO getMultipleChoiceQuizResponseDTO(MultipleChoiceQuiz multipleChoiceQuiz,
                                                                           MultipleChoiceQuizRequestDTO requestDTO) {
        ListeningLessonResponseDTO lessonResponseDTO = lessonService.getById(requestDTO.getLessonId());
        ListeningLesson listeningLesson = entityManager.merge(modelMapper.map(lessonResponseDTO, ListeningLesson.class));

        multipleChoiceQuiz.setQuestion(requestDTO.getQuestion());
        multipleChoiceQuiz.setAnswer(requestDTO.getAnswer());
        multipleChoiceQuiz.setOptionOne(requestDTO.getOptionOne());
        multipleChoiceQuiz.setOptionTwo(requestDTO.getOptionTwo());
        multipleChoiceQuiz.setOptionThree(requestDTO.getOptionThree());
        multipleChoiceQuiz.setOptionFour(requestDTO.getOptionFour());
        multipleChoiceQuiz.setListeningLesson(listeningLesson);

        listeningLesson.addMultipleChoiceQuiz(multipleChoiceQuiz);

        MultipleChoiceQuiz response = choiceQuizRepository.save(multipleChoiceQuiz);
        choiceQuizRepository.flush();

        return modelMapper.map(response, MultipleChoiceQuizResponseDTO.class);
    }
}
