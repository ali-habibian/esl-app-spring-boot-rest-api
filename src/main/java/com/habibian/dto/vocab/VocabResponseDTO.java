/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.dto.vocab;

import com.habibian.dto.example_sentence.ExampleSentenceResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class VocabResponseDTO {
    private Long id;
    private String title;
    private String sentenceInLesson;
    private String description;
    private List<ExampleSentenceResponseDTO> exampleSentences;
    private Long listeningLessonId;
}
