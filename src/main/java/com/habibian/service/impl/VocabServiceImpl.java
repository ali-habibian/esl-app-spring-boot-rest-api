/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service.impl;

import com.habibian.domain.entity.ListeningLesson;
import com.habibian.domain.entity.Vocab;
import com.habibian.dto.listening_lesson.ListeningLessonResponseDTO;
import com.habibian.dto.vocab.VocabRequestDTO;
import com.habibian.dto.vocab.VocabResponseDTO;
import com.habibian.exception.domain.ResourceNotFoundException;
import com.habibian.repository.VocabRepository;
import com.habibian.service.ListeningLessonService;
import com.habibian.service.VocabService;
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
public class VocabServiceImpl implements VocabService {

    private final VocabRepository vocabRepository;
    private final ListeningLessonService lessonService;
    private final ModelMapper modelMapper;

    private final EntityManager entityManager;

    @Override
    public VocabResponseDTO create(VocabRequestDTO requestDTO) {
        ListeningLessonResponseDTO lessonResponseDTO = lessonService.getById(requestDTO.getListeningLessonId());
        ListeningLesson lesson = entityManager.merge(modelMapper.map(lessonResponseDTO, ListeningLesson.class));

        Vocab vocab = new Vocab();

        return getVocabResponseDTO(requestDTO, vocab, lesson);
    }

    @Override
    public VocabResponseDTO getById(long id) {
        Vocab vocab = vocabRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Vocab", "ID", id));
        return modelMapper.map(vocab, VocabResponseDTO.class);
    }

    @Override
    public List<VocabResponseDTO> getAll() {
        List<Vocab> vocabList = vocabRepository.findAll();
        return vocabList.stream()
                .map(vocab -> modelMapper.map(vocab, VocabResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<VocabResponseDTO> getAllWithPagination(Pageable pageable) {
        Page<Vocab> vocabPage = vocabRepository.findAll(pageable);
        return vocabPage.map(vocab -> modelMapper.map(vocab, VocabResponseDTO.class));
    }

    @Override
    public List<VocabResponseDTO> getAllByLessonId(long lessonId) {
        List<Vocab> vocabList = vocabRepository.findAllByListeningLesson_Id(lessonId);
        return vocabList.stream()
                .map(vocab -> modelMapper.map(vocab, VocabResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public VocabResponseDTO update(long id, VocabRequestDTO requestDTO) {
        Vocab vocab = vocabRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Vocab", "ID", id));

        ListeningLessonResponseDTO lessonResponseDTO = lessonService.getById(requestDTO.getListeningLessonId());
        ListeningLesson lesson = entityManager.merge(modelMapper.map(lessonResponseDTO, ListeningLesson.class));

        return getVocabResponseDTO(requestDTO, vocab, lesson);
    }

    @Override
    public void delete(long id) {
        Vocab vocab = vocabRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Vocab", "ID", id));

        vocabRepository.delete(vocab);
    }

    private VocabResponseDTO getVocabResponseDTO(VocabRequestDTO requestDTO, Vocab vocab, ListeningLesson lesson) {
        vocab.setTitle(requestDTO.getTitle());
        vocab.setSentenceInLesson(requestDTO.getSentenceInLesson());
        vocab.setDescription(requestDTO.getDescription());
        vocab.setListeningLesson(lesson);
        lesson.addVocab(vocab);

        Vocab response = vocabRepository.save(vocab);
        vocabRepository.flush();

        return modelMapper.map(response, VocabResponseDTO.class);
    }
}
