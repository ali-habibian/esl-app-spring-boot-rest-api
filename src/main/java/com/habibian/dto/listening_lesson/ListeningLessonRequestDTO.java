/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.dto.listening_lesson;

import com.habibian.enumeration.LessonLevel;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ListeningLessonRequestDTO {
    private LessonLevel level;
    private String title;
    private String description;
    private MultipartFile audioFile;
    private MultipartFile scriptFile;
}
