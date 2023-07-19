/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * The FileSystemStorageService interface provides methods to initialize, save, and load files in a file system storage.
 */
public interface FileSystemStorageService {

    /**
     * Initializes the file system storage.
     */
    void init();

    /**
     * Saves a file to the file system storage.
     *
     * @param file The MultipartFile object representing the file to be saved.
     * @return A String representing the generated file name or identifier.
     */
    String saveFile(MultipartFile file);

    /**
     * Loads a file from the file system storage.
     *
     * @param fileName The name or identifier of the file to be loaded.
     * @return The Resource object representing the loaded file.
     */
    Resource loadFile(String fileName);
}

