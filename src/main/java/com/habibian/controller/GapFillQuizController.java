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

    @PostMapping
    public ResponseEntity<GapFillQuizResponseDTO> create(@RequestBody GapFillQuizRequestDTO requestDTO) {
        GapFillQuizResponseDTO responseDTO = gapFillQuizService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GapFillQuizResponseDTO> getById(@PathVariable long id) {
        GapFillQuizResponseDTO responseDTO = gapFillQuizService.getById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<GapFillQuizResponseDTO>> getAllByLessonId(@PathVariable long lessonId) {
        List<GapFillQuizResponseDTO> responseDTOs = gapFillQuizService.getAllByLessonId(lessonId);
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping(params = {"lessonId", "page", "size"})
    public ResponseEntity<Page<GapFillQuizResponseDTO>> getAllByLessonId(@RequestParam long lessonId,
                                                                         @PageableDefault Pageable pageable) {
        Page<GapFillQuizResponseDTO> responseDTOs = gapFillQuizService.getAllByLessonId(lessonId, pageable);
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GapFillQuizResponseDTO> update(@PathVariable long id, @RequestBody GapFillQuizRequestDTO requestDTO) {
        GapFillQuizResponseDTO responseDTO = gapFillQuizService.update(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        gapFillQuizService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
