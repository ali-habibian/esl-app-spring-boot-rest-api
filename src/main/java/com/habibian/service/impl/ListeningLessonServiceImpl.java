package com.habibian.service.impl;

import com.habibian.domain.entity.ListeningLesson;
import com.habibian.dto.listening_lesson.ListeningLessonRequestDTO;
import com.habibian.dto.listening_lesson.ListeningLessonResponseDTO;
import com.habibian.enumeration.LessonLevel;
import com.habibian.exception.domain.ResourceNotFoundException;
import com.habibian.repository.ListeningLessonRepository;
import com.habibian.service.ListeningLessonService;
import lombok.RequiredArgsConstructor;
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
    public ListeningLessonResponseDTO create(ListeningLessonRequestDTO request) {
        ListeningLesson listeningLesson = modelMapper.map(request, ListeningLesson.class);
        ListeningLesson response = lessonRepository.save(listeningLesson);
        return modelMapper.map(response, ListeningLessonResponseDTO.class);
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
    public ListeningLessonResponseDTO update(long id, ListeningLessonRequestDTO request) {
        ListeningLesson listeningLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ListeningLesson", "id", id));

        listeningLesson.setLevel(request.getLevel());
        listeningLesson.setTitle(request.getTitle());
        listeningLesson.setDescription(request.getDescription());
        listeningLesson.setAudioFileUrl(request.getAudioFileUrl());
        listeningLesson.setScriptFileUrl(request.getScriptFileUrl());

        ListeningLesson response = lessonRepository.save(listeningLesson);
        return modelMapper.map(response, ListeningLessonResponseDTO.class);
    }

    @Override
    public void delete(long id) {
        ListeningLesson listeningLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ListeningLesson", "id", id));
        lessonRepository.delete(listeningLesson);
    }
}
