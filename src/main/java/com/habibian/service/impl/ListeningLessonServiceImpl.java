/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service.impl;

import com.habibian.domain.entity.ListeningLesson;
import com.habibian.dto.listening_lesson.ListeningLessonRequestDTO;
import com.habibian.dto.listening_lesson.ListeningLessonResponseDTO;
import com.habibian.enumeration.LessonLevel;
import com.habibian.exception.domain.ResourceNotFoundException;
import com.habibian.repository.ListeningLessonRepository;
import com.habibian.service.ListeningLessonService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ListeningLessonServiceImpl implements ListeningLessonService {
    private final ListeningLessonRepository lessonRepository;
    private final ModelMapper modelMapper;

    @Override
    public ListeningLessonResponseDTO create(ListeningLessonRequestDTO request, String audioFileUrl, String scriptFileUrl) {
        ListeningLesson listeningLesson = new ListeningLesson();

        listeningLesson.setLessonId(generateLessonId());
        return getListeningLessonResponseDTO(request, audioFileUrl, scriptFileUrl, listeningLesson);
    }

    @Override
    public ListeningLessonResponseDTO getById(long id) {
        ListeningLesson listeningLesson = lessonRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ListeningLesson", "ID", id));
        return modelMapper.map(listeningLesson, ListeningLessonResponseDTO.class);
    }

    @Override
    public List<ListeningLessonResponseDTO> getAll() {
        List<ListeningLesson> lessons = lessonRepository.findAll();
        return lessons.stream()
                .map(lesson -> modelMapper.map(lesson, ListeningLessonResponseDTO.class))
                .toList();
    }

    @Override
    public Page<ListeningLessonResponseDTO> getAllWithPagination(Pageable pageable) {
        Page<ListeningLesson> lessons = lessonRepository.findAll(pageable);
        return lessons.map(lesson -> modelMapper.map(lesson, ListeningLessonResponseDTO.class));
    }

    @Override
    public List<ListeningLessonResponseDTO> getAllByLevel(LessonLevel level) {
        List<ListeningLesson> lessons = lessonRepository.findAllByLevel(level);
        return lessons.stream()
                .map(lesson -> modelMapper.map(lesson, ListeningLessonResponseDTO.class))
                .toList();
    }

    @Override
    public Page<ListeningLessonResponseDTO> getAllByLevelWithPagination(LessonLevel level, Pageable pageable) {
        Page<ListeningLesson> lessons = lessonRepository.findAllByLevel(level, pageable);
        return lessons.map(lesson -> modelMapper.map(lesson, ListeningLessonResponseDTO.class));
    }

    @Override
    public ListeningLessonResponseDTO update(long id, ListeningLessonRequestDTO request, String audioFileUrl, String scriptFileUrl) {
        ListeningLesson listeningLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ListeningLesson", "id", id));

        return getListeningLessonResponseDTO(request, audioFileUrl, scriptFileUrl, listeningLesson);
    }

    @Override
    public void delete(long id) {
        ListeningLesson listeningLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ListeningLesson", "id", id));
        lessonRepository.delete(listeningLesson);
    }


    private String generateLessonId() {
        return RandomStringUtils.randomNumeric(10);
    }

    private ListeningLessonResponseDTO getListeningLessonResponseDTO(ListeningLessonRequestDTO request, String audioFileUrl, String scriptFileUrl, ListeningLesson listeningLesson) {
        listeningLesson.setLevel(request.getLevel());
        listeningLesson.setTitle(request.getTitle());
        listeningLesson.setDescription(request.getDescription());
        listeningLesson.setAudioFileUrl(audioFileUrl);
        listeningLesson.setScriptFileUrl(scriptFileUrl);

        ListeningLesson response = lessonRepository.save(listeningLesson);
        return modelMapper.map(response, ListeningLessonResponseDTO.class);
    }
}
