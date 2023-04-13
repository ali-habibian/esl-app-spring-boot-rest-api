/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.controller;

import com.habibian.dto.FileResponse;
import com.habibian.service.FileSystemStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FileController {
    private final FileSystemStorageService fileSystemStorageService;

    @PostMapping("/uploadfile")
    public ResponseEntity<FileResponse> uploadSingleFile (@RequestParam("file") MultipartFile file) {
        String upfile = fileSystemStorageService.saveFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/download/")
                .path(upfile)
                .toUriString();

        return ResponseEntity.status(HttpStatus.OK).body(
                new FileResponse(upfile,fileDownloadUri,"File uploaded with success!")
        );
    }

    @PostMapping("/uploadfiles")
    public ResponseEntity<List<FileResponse>> uploadMultipleFiles (@RequestParam("files") MultipartFile[] files) {

        List<FileResponse> responses = Arrays.stream(files)
                .map(
                        file -> {
                            String upfile = fileSystemStorageService.saveFile(file);
                            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                    .path("/api/v1/download/")
                                    .path(upfile)
                                    .toUriString();
                            return new FileResponse(upfile,fileDownloadUri,"File uploaded with success!");
                        }
                )
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        Resource resource = fileSystemStorageService.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
