package com.habibian.service.impl;

import com.habibian.dto.example_sentence.ExampleSentenceRequestDTO;
import com.habibian.dto.example_sentence.ExampleSentenceResponseDTO;
import com.habibian.service.ExampleSentenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExampleSentenceServiceImplTest {

    @Autowired
    private ExampleSentenceService sentenceService;

    @Test
    void create() {
        ExampleSentenceRequestDTO requestDTO = ExampleSentenceRequestDTO.builder()
                .sentence("Example sentence for vocab 1")
                .vocabId(1)
                .build();

        ExampleSentenceResponseDTO responseDTO = sentenceService.create(requestDTO);
        System.out.println(responseDTO.toString());

        assertEquals(1L, responseDTO.getId());
    }

    @Test
    void getById() {
    }

    @Test
    void getAllByVocabId() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}