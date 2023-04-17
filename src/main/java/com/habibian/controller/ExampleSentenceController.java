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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/example-sentences")
public class ExampleSentenceController {
    private final ExampleSentenceService exampleSentenceService;

    @PostMapping
    public ResponseEntity<ExampleSentenceResponseDTO> create(@RequestBody ExampleSentenceRequestDTO requestDTO) {
        ExampleSentenceResponseDTO responseDTO = exampleSentenceService.create(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExampleSentenceResponseDTO> getById(@PathVariable long id) {
        ExampleSentenceResponseDTO responseDTO = exampleSentenceService.getById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/vocab/{vocabId}")
    public ResponseEntity<List<ExampleSentenceResponseDTO>> getAllByVocabId(@PathVariable long vocabId) {
        List<ExampleSentenceResponseDTO> responseDTOs = exampleSentenceService.getAllByVocabId(vocabId);
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExampleSentenceResponseDTO> update(@PathVariable long id, @RequestBody ExampleSentenceRequestDTO requestDTO) {
        ExampleSentenceResponseDTO responseDTO = exampleSentenceService.update(id, requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        exampleSentenceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
