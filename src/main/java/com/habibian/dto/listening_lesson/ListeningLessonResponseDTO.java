package com.habibian.dto.listening_lesson;

import com.habibian.domain.entity.GapFillQuiz;
import com.habibian.domain.entity.MultipleChoiceQuiz;
import com.habibian.domain.entity.Vocab;
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
    private List<Vocab> vocabs; // TODO Change to VocabResponseDTO
    private List<MultipleChoiceQuiz> multipleChoiceQuizzes; // TODO Change to DTO
    private List<GapFillQuiz> gapFillQuizzes; // TODO Change to DTO
}
