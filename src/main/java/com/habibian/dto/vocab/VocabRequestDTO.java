/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.dto.vocab;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class VocabRequestDTO {
    private String title;
    private String sentenceInLesson;
    private String description;
    private Long listeningLessonId;
}
