/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.controller;

import com.habibian.dto.gap_fill_quiz.GapFillQuizRequestDTO;
import com.habibian.dto.gap_fill_quiz.GapFillQuizResponseDTO;
import com.habibian.service.GapFillQuizService;
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
@RequestMapping("/api/v1/gap-fill-quiz")
public class GapFillQuizController {

    private final GapFillQuizService gapFillQuizService;

    /**
     * Handles the POST request to create a new gap fill quiz.
     *
     * @param requestDTO The GapFillQuizRequestDTO object containing the request data.
     * @return ResponseEntity with the GapFillQuizResponseDTO and HttpStatus.CREATED.
     */
    @PostMapping
    public ResponseEntity<GapFillQuizResponseDTO> create(@RequestBody GapFillQuizRequestDTO requestDTO) {
        GapFillQuizResponseDTO responseDTO = gapFillQuizService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Handles the GET request to retrieve a gap fill quiz by its ID.
     *
     * @param id The ID of the gap fill quiz to retrieve.
     * @return ResponseEntity with the GapFillQuizResponseDTO and HttpStatus.OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GapFillQuizResponseDTO> getById(@PathVariable long id) {
        GapFillQuizResponseDTO responseDTO = gapFillQuizService.getById(id);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Handles the GET request to retrieve all gap fill quizzes associated with a lesson ID.
     *
     * @param lessonId The ID of the lesson to retrieve gap fill quizzes for.
     * @return ResponseEntity with a list of GapFillQuizResponseDTOs and HttpStatus.OK.
     */
    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<GapFillQuizResponseDTO>> getAllByLessonId(@PathVariable long lessonId) {
        List<GapFillQuizResponseDTO> responseDTOs = gapFillQuizService.getAllByLessonId(lessonId);
        return ResponseEntity.ok(responseDTOs);
    }

    /**
     * Handles the GET request to retrieve all gap fill quizzes associated with a lesson ID
     * using pagination.
     *
     * @param lessonId The ID of the lesson to retrieve gap fill quizzes for.
     * @param pageable The pageable object containing page and size information.
     * @return ResponseEntity with a page of GapFillQuizResponseDTOs and HttpStatus.OK.
     */
    @GetMapping(params = {"lessonId", "page", "size"})
    public ResponseEntity<Page<GapFillQuizResponseDTO>> getAllByLessonId(@RequestParam long lessonId,
                                                                         @PageableDefault Pageable pageable) {
        Page<GapFillQuizResponseDTO> responseDTOs = gapFillQuizService.getAllByLessonId(lessonId, pageable);
        return ResponseEntity.ok(responseDTOs);
    }

    /**
     * Handles the PUT request to update a gap fill quiz by its ID.
     *
     * @param id         The ID of the gap fill quiz to update.
     * @param requestDTO The GapFillQuizRequestDTO object containing the request data.
     * @return ResponseEntity with the GapFillQuizResponseDTO and HttpStatus.OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<GapFillQuizResponseDTO> update(@PathVariable long id, @RequestBody GapFillQuizRequestDTO requestDTO) {
        GapFillQuizResponseDTO responseDTO = gapFillQuizService.update(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Handles the DELETE request to delete a gap fill quiz by its ID.
     *
     * @param id The ID of the gap fill quiz to delete.
     * @return ResponseEntity with HttpStatus.NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        gapFillQuizService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

