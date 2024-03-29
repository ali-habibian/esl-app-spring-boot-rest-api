/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service.impl;

import com.habibian.dto.listening_lesson.ListeningLessonRequestDTO;
import com.habibian.dto.listening_lesson.ListeningLessonResponseDTO;
import com.habibian.enumeration.LessonLevel;
import com.habibian.service.ListeningLessonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ListeningLessonServiceImplTest {

    @Autowired
    private ListeningLessonService lessonService;

    @Test
    void create() {

    }

    @Test
    void getById() {
        ListeningLessonResponseDTO responseDTO = lessonService.getById(1L);

        System.out.println(responseDTO);

        assertEquals(2, responseDTO.getVocabs().size());
    }

    @Test
    void getAll() {
    }

    @Test
    void getAllWithPagination() {
    }

    @Test
    void getAllByLevel() {
    }

    @Test
    void getAllByLevelWithPagination() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}