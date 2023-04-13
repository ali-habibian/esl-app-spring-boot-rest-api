/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.dto.listening_lesson;

import com.habibian.dto.gap_fill_quiz.GapFillQuizResponseDTO;
import com.habibian.dto.multiple_choice_quiz.MultipleChoiceQuizResponseDTO;
import com.habibian.dto.vocab.VocabResponseDTO;
import com.habibian.enumeration.LessonLevel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ListeningLessonResponseDTO {
    private Long id;
    private LessonLevel level;
    private String title;
    private String description;
    private String audioFileUrl;
    private String scriptFileUrl;
    private List<VocabResponseDTO> vocabs;
    private List<MultipleChoiceQuizResponseDTO> multipleChoiceQuizzes;
    private List<GapFillQuizResponseDTO> gapFillQuizzes;
}
