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

    @PostMapping
    public ResponseEntity<ListeningLessonResponseDTO> create(@ModelAttribute ListeningLessonRequestDTO request) {
        ResponseEntity<FileResponse> audioFileResponseEntity = fileController.uploadSingleFile(request.getAudioFile());
        ResponseEntity<FileResponse> scriptFileResponseEntity = fileController.uploadSingleFile(request.getScriptFile());
        String audioFileUrl = Objects.requireNonNull(audioFileResponseEntity.getBody()).getFileUrl();
        String scriptFileUrl = Objects.requireNonNull(scriptFileResponseEntity.getBody()).getFileUrl();

        ListeningLessonResponseDTO response = listeningLessonService.create(request, audioFileUrl, scriptFileUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListeningLessonResponseDTO> getById(@PathVariable long id) {
        ListeningLessonResponseDTO response = listeningLessonService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ListeningLessonResponseDTO>> getAll() {
        List<ListeningLessonResponseDTO> response = listeningLessonService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = {"page", "size"}) // Example url: /listening-lessons?page=0&size=10
    public ResponseEntity<Page<ListeningLessonResponseDTO>> getAllWithPagination(@PageableDefault Pageable pageable) {
        Page<ListeningLessonResponseDTO> response = listeningLessonService.getAllWithPagination(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/level/{level}") // Example url: /listening-lessons/level/A1
    public ResponseEntity<List<ListeningLessonResponseDTO>> getAllByLevel(@PathVariable LessonLevel level) {
        List<ListeningLessonResponseDTO> response = listeningLessonService.getAllByLevel(level);
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = {"level", "page", "size"}) // Example url: /listening-lessons?level=A1&page=1&size=5
    public ResponseEntity<Page<ListeningLessonResponseDTO>> getAllByLevelWithPagination(@RequestParam LessonLevel level, @PageableDefault Pageable pageable) {
        Page<ListeningLessonResponseDTO> response = listeningLessonService.getAllByLevelWithPagination(level, pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListeningLessonResponseDTO> update(@PathVariable long id, @ModelAttribute ListeningLessonRequestDTO request) {
        ResponseEntity<FileResponse> audioFileResponseEntity = fileController.uploadSingleFile(request.getAudioFile());
        ResponseEntity<FileResponse> scriptFileResponseEntity = fileController.uploadSingleFile(request.getScriptFile());
        String audioFileUrl = Objects.requireNonNull(audioFileResponseEntity.getBody()).getFileUrl();
        String scriptFileUrl = Objects.requireNonNull(scriptFileResponseEntity.getBody()).getFileUrl();

        ListeningLessonResponseDTO response = listeningLessonService.update(id, request, audioFileUrl, scriptFileUrl);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        listeningLessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
