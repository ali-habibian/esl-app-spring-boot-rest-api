/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileSystemStorageService {
    void init();

    String saveFile(MultipartFile file);

    Resource loadFile(String fileName);
}
