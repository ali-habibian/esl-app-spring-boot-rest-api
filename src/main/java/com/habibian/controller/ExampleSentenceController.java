/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.controller;

import com.habibian.dto.example_sentence.ExampleSentenceRequestDTO;
import com.habibian.dto.example_sentence.ExampleSentenceResponseDTO;
import com.habibian.service.ExampleSentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/example-sentences")
public class ExampleSentenceController {
    private final ExampleSentenceService exampleSentenceService;

    /**
     * Creates a new example sentence.
     *
     * @param requestDTO The ExampleSentenceRequestDTO object containing the request data.
     * @return ResponseEntity with the ExampleSentenceResponseDTO and HttpStatus.CREATED.
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ExampleSentenceResponseDTO> create(@RequestBody ExampleSentenceRequestDTO requestDTO) {
        ExampleSentenceResponseDTO responseDTO = exampleSentenceService.create(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Retrieves an example sentence by its ID.
     *
     * @param id The ID of the example sentence to retrieve.
     * @return ResponseEntity with the ExampleSentenceResponseDTO and HttpStatus.OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExampleSentenceResponseDTO> getById(@PathVariable long id) {
        ExampleSentenceResponseDTO responseDTO = exampleSentenceService.getById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Retrieves all example sentences associated with a vocabulary ID.
     *
     * @param vocabId The ID of the vocabulary to retrieve example sentences for.
     * @return ResponseEntity with a list of ExampleSentenceResponseDTOs and HttpStatus.OK.
     */
    @GetMapping("/vocab/{vocabId}")
    public ResponseEntity<List<ExampleSentenceResponseDTO>> getAllByVocabId(@PathVariable long vocabId) {
        List<ExampleSentenceResponseDTO> responseDTOs = exampleSentenceService.getAllByVocabId(vocabId);
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    /**
     * Updates an example sentence by its ID.
     *
     * @param id         The ID of the example sentence to update.
     * @param requestDTO The ExampleSentenceRequestDTO object containing the request data.
     * @return ResponseEntity with the ExampleSentenceResponseDTO and HttpStatus.OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExampleSentenceResponseDTO> update(@PathVariable long id, @RequestBody ExampleSentenceRequestDTO requestDTO) {
        ExampleSentenceResponseDTO responseDTO = exampleSentenceService.update(id, requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Deletes an example sentence by its ID.
     *
     * @param id The ID of the example sentence to delete.
     * @return ResponseEntity with HttpStatus.NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        exampleSentenceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
