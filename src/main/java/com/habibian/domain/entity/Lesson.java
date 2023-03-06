package com.habibian.domain.entity;

import com.habibian.enumeration.LessonLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private LessonLevel level;
    private String title;
    private String description;
    private String audioFileUrl;
    private String scriptFileUrl;

    @OneToMany
    private List<Vocab> vocabs;

    @OneToMany
    private List<MultipleChoiceQuiz> multipleChoiceQuizzes; // These quizzes are for listening

    @OneToMany
    private List<GapFillQuiz> gapFillQuizzes; // These quizzes are for vocabs
}
