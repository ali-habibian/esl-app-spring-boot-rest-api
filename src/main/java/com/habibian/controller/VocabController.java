/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.controller;

import com.habibian.dto.vocab.VocabRequestDTO;
import com.habibian.dto.vocab.VocabResponseDTO;
import com.habibian.service.VocabService;
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
@RequestMapping("/api/v1/vocabs")
public class VocabController {
    private final VocabService vocabService;

    @PostMapping
    public ResponseEntity<VocabResponseDTO> create(@RequestBody VocabRequestDTO requestDTO) {
        VocabResponseDTO responseDTO = vocabService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VocabResponseDTO> getById(@PathVariable long id) {
        VocabResponseDTO responseDTO = vocabService.getById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<VocabResponseDTO>> getAll() {
        List<VocabResponseDTO> responseDTOs = vocabService.getAll();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<VocabResponseDTO>> getAllWithPagination(@PageableDefault Pageable pageable) {
        Page<VocabResponseDTO> responseDTOs = vocabService.getAllWithPagination(pageable);
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<VocabResponseDTO>> getAllByLessonId(@PathVariable long lessonId) {
        List<VocabResponseDTO> responseDTOs = vocabService.getAllByLessonId(lessonId);
        return ResponseEntity.ok(responseDTOs);
    }

    // TODO - add method getAllByLessonId with pagination
    @PutMapping("/{id}")
    public ResponseEntity<VocabResponseDTO> update(@PathVariable long id, @RequestBody VocabRequestDTO requestDTO) {
        VocabResponseDTO responseDTO = vocabService.update(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        vocabService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
