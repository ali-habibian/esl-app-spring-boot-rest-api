/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.controller;

import com.habibian.dto.multiple_choice_quiz.MultipleChoiceQuizRequestDTO;
import com.habibian.dto.multiple_choice_quiz.MultipleChoiceQuizResponseDTO;
import com.habibian.service.MultipleChoiceQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/multiple-choice-quiz")
public class MultipleChoiceQuizController {

    private final MultipleChoiceQuizService multipleChoiceQuizService;

    @PostMapping
    public ResponseEntity<MultipleChoiceQuizResponseDTO> create(@RequestBody MultipleChoiceQuizRequestDTO requestDTO) {
        MultipleChoiceQuizResponseDTO responseDTO = multipleChoiceQuizService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MultipleChoiceQuizResponseDTO> getById(@PathVariable long id) {
        MultipleChoiceQuizResponseDTO responseDTO = multipleChoiceQuizService.getById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<MultipleChoiceQuizResponseDTO>> getAllByLessonId(@PathVariable long lessonId) {
        List<MultipleChoiceQuizResponseDTO> responseDTOs = multipleChoiceQuizService.getAllByLessonId(lessonId);
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping(params = {"lessonId", "page", "size"})
    public ResponseEntity<Page<MultipleChoiceQuizResponseDTO>> getAllByLessonId(@RequestParam long lessonId,
                                                                                @PageableDefault Pageable pageable) {
        Page<MultipleChoiceQuizResponseDTO> responseDTOs = multipleChoiceQuizService.getAllByLessonId(lessonId, pageable);
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MultipleChoiceQuizResponseDTO> update(@PathVariable long id, @RequestBody MultipleChoiceQuizRequestDTO requestDTO) {
        MultipleChoiceQuizResponseDTO responseDTO = multipleChoiceQuizService.update(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        multipleChoiceQuizService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
