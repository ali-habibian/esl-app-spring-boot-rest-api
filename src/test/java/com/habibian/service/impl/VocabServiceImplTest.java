package com.habibian.service.impl;

import com.habibian.dto.vocab.VocabRequestDTO;
import com.habibian.dto.vocab.VocabResponseDTO;
import com.habibian.service.VocabService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VocabServiceImplTest {

    @Autowired
    private VocabService vocabService;

    @Test
    void create() {
        VocabRequestDTO requestDTO = VocabRequestDTO.builder()
                .title("Vocab 2")
                .description("Desc Vocab 2")
                .sentenceInLesson("Sentence 2 in lesson 1")
                .listeningLessonId(1L)
                .build();

        VocabResponseDTO vocabResponseDTO = vocabService.create(requestDTO);
        System.out.println(vocabResponseDTO.toString());

        assertEquals(2L, vocabResponseDTO.getId());
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getAllWithPagination() {
    }

    @Test
    void getAllByLessonId() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}