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
    private List<Vocab> vocabs;
    private List<MultipleChoiceQuiz> multipleChoiceQuizzes; // These quizzes are for listening
    private List<GapFillQuiz> gapFillQuizzes; // These quizzes are for vocabs
}
