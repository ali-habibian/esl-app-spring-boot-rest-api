/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.dto.vocab;

import com.habibian.domain.entity.ExampleSentence;
import com.habibian.domain.entity.ListeningLesson;
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
    private List<ExampleSentence> exampleSentences; // TODO Change to DTO
    private ListeningLesson listeningLesson; // TODO Change to DTO
}
