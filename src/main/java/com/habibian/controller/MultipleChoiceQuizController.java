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

    /**
     * Handles the POST request to create a new multiple choice quiz.
     *
     * @param requestDTO The MultipleChoiceQuizRequestDTO object containing the request data.
     * @return ResponseEntity with the MultipleChoiceQuizResponseDTO and HttpStatus.CREATED.
     */
    @PostMapping
    public ResponseEntity<MultipleChoiceQuizResponseDTO> create(@RequestBody MultipleChoiceQuizRequestDTO requestDTO) {
        MultipleChoiceQuizResponseDTO responseDTO = multipleChoiceQuizService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Handles the GET request to retrieve a multiple choice quiz by its ID.
     *
     * @param id The ID of the multiple choice quiz to retrieve.
     * @return ResponseEntity with the MultipleChoiceQuizResponseDTO and HttpStatus.OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MultipleChoiceQuizResponseDTO> getById(@PathVariable long id) {
        MultipleChoiceQuizResponseDTO responseDTO = multipleChoiceQuizService.getById(id);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Handles the GET request to retrieve all multiple choice quizzes associated with a lesson ID.
     *
     * @param lessonId The ID of the lesson to retrieve multiple choice quizzes for.
     * @return ResponseEntity with a list of MultipleChoiceQuizResponseDTOs and HttpStatus.OK.
     */
    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<MultipleChoiceQuizResponseDTO>> getAllByLessonId(@PathVariable long lessonId) {
        List<MultipleChoiceQuizResponseDTO> responseDTOs = multipleChoiceQuizService.getAllByLessonId(lessonId);
        return ResponseEntity.ok(responseDTOs);
    }

    /**
     * Handles the GET request to retrieve all multiple choice quizzes associated with a lesson ID using pagination.
     *
     * @param lessonId The ID of the lesson to retrieve multiple choice quizzes for.
     * @param pageable The pageable object containing page and size information.
     * @return ResponseEntity with a page of MultipleChoiceQuizResponseDTOs and HttpStatus.OK.
     */
    @GetMapping(params = {"lessonId", "page", "size"})
    public ResponseEntity<Page<MultipleChoiceQuizResponseDTO>> getAllByLessonId(@RequestParam long lessonId,
                                                                                @PageableDefault Pageable pageable) {
        Page<MultipleChoiceQuizResponseDTO> responseDTOs = multipleChoiceQuizService.getAllByLessonId(lessonId, pageable);
        return ResponseEntity.ok(responseDTOs);
    }

    /**
     * Handles the PUT request to update a multiple choice quiz by its ID.
     *
     * @param id         The ID of the multiple choice quiz to update.
     * @param requestDTO The MultipleChoiceQuizRequestDTO object containing the request data.
     * @return ResponseEntity with the MultipleChoiceQuizResponseDTO and HttpStatus.OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MultipleChoiceQuizResponseDTO> update(@PathVariable long id, @RequestBody MultipleChoiceQuizRequestDTO requestDTO) {
        MultipleChoiceQuizResponseDTO responseDTO = multipleChoiceQuizService.update(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Handles the DELETE request to delete a multiple choice quiz by its ID.
     *
     * @param id The ID of the multiple choice quiz to delete.
     * @return ResponseEntity with HttpStatus.NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        multipleChoiceQuizService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
