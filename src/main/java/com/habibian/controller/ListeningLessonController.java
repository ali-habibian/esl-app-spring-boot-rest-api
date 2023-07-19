/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.controller;

import com.habibian.dto.FileResponse;
import com.habibian.dto.listening_lesson.ListeningLessonRequestDTO;
import com.habibian.dto.listening_lesson.ListeningLessonResponseDTO;
import com.habibian.enumeration.LessonLevel;
import com.habibian.service.ListeningLessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/listening-lessons")
public class ListeningLessonController {

    private final ListeningLessonService listeningLessonService;
    private final FileController fileController;

    /**
     * Handles the POST request to create a new listening lesson.
     *
     * @param request The ListeningLessonRequestDTO object containing the request data.
     * @return ResponseEntity with the ListeningLessonResponseDTO and HttpStatus.CREATED.
     */
    @PostMapping
    public ResponseEntity<ListeningLessonResponseDTO> create(@ModelAttribute ListeningLessonRequestDTO request) {
        ResponseEntity<FileResponse> audioFileResponseEntity = fileController.uploadSingleFile(request.getAudioFile());
        ResponseEntity<FileResponse> scriptFileResponseEntity = fileController.uploadSingleFile(request.getScriptFile());
        String audioFileUrl = Objects.requireNonNull(audioFileResponseEntity.getBody()).getFileUrl();
        String scriptFileUrl = Objects.requireNonNull(scriptFileResponseEntity.getBody()).getFileUrl();

        ListeningLessonResponseDTO response = listeningLessonService.create(request, audioFileUrl, scriptFileUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Handles the GET request to retrieve a listening lesson by its ID.
     *
     * @param id The ID of the listening lesson to retrieve.
     * @return ResponseEntity with the ListeningLessonResponseDTO and HttpStatus.OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ListeningLessonResponseDTO> getById(@PathVariable long id) {
        ListeningLessonResponseDTO response = listeningLessonService.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Handles the GET request to retrieve all listening lessons.
     *
     * @return ResponseEntity with a list of ListeningLessonResponseDTOs and HttpStatus.OK.
     */
    @GetMapping
    public ResponseEntity<List<ListeningLessonResponseDTO>> getAll() {
        List<ListeningLessonResponseDTO> response = listeningLessonService.getAll();
        return ResponseEntity.ok(response);
    }

    /**
     * Handles the GET request to retrieve all listening lessons with pagination.
     *
     * @param pageable The pageable object containing page and size information.
     * @return ResponseEntity with a page of ListeningLessonResponseDTOs and HttpStatus.OK.
     */
    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<ListeningLessonResponseDTO>> getAllWithPagination(@PageableDefault Pageable pageable) {
        Page<ListeningLessonResponseDTO> response = listeningLessonService.getAllWithPagination(pageable);
        return ResponseEntity.ok(response);
    }

    /**
     * Handles the GET request to retrieve all listening lessons by a specific level.
     *
     * @param level The level of the listening lessons to retrieve.
     * @return ResponseEntity with a list of ListeningLessonResponseDTOs and HttpStatus.OK.
     */
    @GetMapping("/level/{level}")
    public ResponseEntity<List<ListeningLessonResponseDTO>> getAllByLevel(@PathVariable LessonLevel level) {
        List<ListeningLessonResponseDTO> response = listeningLessonService.getAllByLevel(level);
        return ResponseEntity.ok(response);
    }

    /**
     * Handles the GET request to retrieve all listening lessons by a specific level with pagination.
     *
     * @param level    The level of the listening lessons to retrieve.
     * @param pageable The pageable object containing page and size information.
     * @return ResponseEntity with a page of ListeningLessonResponseDTOs and HttpStatus.OK.
     */
    @GetMapping(params = {"level", "page", "size"})
    public ResponseEntity<Page<ListeningLessonResponseDTO>> getAllByLevelWithPagination(@RequestParam LessonLevel level, @PageableDefault Pageable pageable) {
        Page<ListeningLessonResponseDTO> response = listeningLessonService.getAllByLevelWithPagination(level, pageable);
        return ResponseEntity.ok(response);
    }

    /**
     * Handles the PUT request to update a listening lesson by its ID.
     *
     * @param id      The ID of the listening lesson to update.
     * @param request The ListeningLessonRequestDTO object containing the request data.
     * @return ResponseEntity with the ListeningLessonResponseDTO and HttpStatus.OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ListeningLessonResponseDTO> update(@PathVariable long id, @ModelAttribute ListeningLessonRequestDTO request) {
        ResponseEntity<FileResponse> audioFileResponseEntity = fileController.uploadSingleFile(request.getAudioFile());
        ResponseEntity<FileResponse> scriptFileResponseEntity = fileController.uploadSingleFile(request.getScriptFile());
        String audioFileUrl = Objects.requireNonNull(audioFileResponseEntity.getBody()).getFileUrl();
        String scriptFileUrl = Objects.requireNonNull(scriptFileResponseEntity.getBody()).getFileUrl();

        ListeningLessonResponseDTO response = listeningLessonService.update(id, request, audioFileUrl, scriptFileUrl);
        return ResponseEntity.ok(response);
    }

    /**
     * Handles the DELETE request to delete a listening lesson by its ID.
     *
     * @param id The ID of the listening lesson to delete.
     * @return ResponseEntity with HttpStatus.NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        listeningLessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
