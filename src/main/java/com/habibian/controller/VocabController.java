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

    /**
     * Handles the POST request to create a new vocabulary.
     *
     * @param requestDTO The VocabRequestDTO object containing the request data.
     * @return ResponseEntity with the VocabResponseDTO and HttpStatus.CREATED.
     */
    @PostMapping
    public ResponseEntity<VocabResponseDTO> create(@RequestBody VocabRequestDTO requestDTO) {
        VocabResponseDTO responseDTO = vocabService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Handles the GET request to retrieve a vocabulary by its ID.
     *
     * @param id The ID of the vocabulary to retrieve.
     * @return ResponseEntity with the VocabResponseDTO and HttpStatus.OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VocabResponseDTO> getById(@PathVariable long id) {
        VocabResponseDTO responseDTO = vocabService.getById(id);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Handles the GET request to retrieve all vocabularies.
     *
     * @return ResponseEntity with a list of VocabResponseDTOs and HttpStatus.OK.
     */
    @GetMapping
    public ResponseEntity<List<VocabResponseDTO>> getAll() {
        List<VocabResponseDTO> responseDTOs = vocabService.getAll();
        return ResponseEntity.ok(responseDTOs);
    }

    /**
     * Handles the GET request to retrieve all vocabularies with pagination.
     *
     * @param pageable The pageable object containing page and size information.
     * @return ResponseEntity with a page of VocabResponseDTOs and HttpStatus.OK.
     */
    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<VocabResponseDTO>> getAllWithPagination(@PageableDefault Pageable pageable) {
        Page<VocabResponseDTO> responseDTOs = vocabService.getAllWithPagination(pageable);
        return ResponseEntity.ok(responseDTOs);
    }

    /**
     * Handles the GET request to retrieve all vocabularies associated with a lesson ID.
     *
     * @param lessonId The ID of the lesson to retrieve vocabularies for.
     * @return ResponseEntity with a list of VocabResponseDTOs and HttpStatus.OK.
     */
    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<VocabResponseDTO>> getAllByLessonId(@PathVariable long lessonId) {
        List<VocabResponseDTO> responseDTOs = vocabService.getAllByLessonId(lessonId);
        return ResponseEntity.ok(responseDTOs);
    }

    /**
     * Handles the GET request to retrieve all vocabularies associated with a lesson ID using pagination.
     *
     * @param lessonId The ID of the lesson to retrieve vocabularies for.
     * @param pageable The pageable object containing page and size information.
     * @return ResponseEntity with a page of VocabResponseDTOs and HttpStatus.OK.
     */
    @GetMapping(params = {"lessonId", "page", "size"})
    public ResponseEntity<Page<VocabResponseDTO>> getAllByLessonIdWithPagination(@RequestParam long lessonId, @PageableDefault Pageable pageable) {
        Page<VocabResponseDTO> responseDTOs = vocabService.getAllByLessonIdWithPagination(lessonId, pageable);
        return ResponseEntity.ok(responseDTOs);
    }

    /**
     * Handles the PUT request to update a vocabulary by its ID.
     *
     * @param id         The ID of the vocabulary to update.
     * @param requestDTO The VocabRequestDTO object containing the request data.
     * @return ResponseEntity with the VocabResponseDTO and HttpStatus.OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<VocabResponseDTO> update(@PathVariable long id, @RequestBody VocabRequestDTO requestDTO) {
        VocabResponseDTO responseDTO = vocabService.update(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Handles the DELETE request to delete a vocabulary by its ID.
     *
     * @param id The ID of the vocabulary to delete.
     * @return ResponseEntity with HttpStatus.NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        vocabService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
