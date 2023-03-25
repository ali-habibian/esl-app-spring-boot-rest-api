package com.habibian.domain.entity;

import com.habibian.enumeration.LessonLevel;
import com.habibian.exception.domain.ResourceNotFoundException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
public class ListeningLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private LessonLevel level;
    private String title;
    private String description;
    private String audioFileUrl;
    private String scriptFileUrl;

    @OneToMany(mappedBy = "listeningLesson", cascade = CascadeType.REMOVE)
    private List<Vocab> vocabs;

    @OneToMany(mappedBy = "listeningLesson", cascade = CascadeType.REMOVE)
    private List<MultipleChoiceQuiz> multipleChoiceQuizzes; // These quizzes are for listening

    @OneToMany(mappedBy = "listeningLesson", cascade = CascadeType.REMOVE)
    private List<GapFillQuiz> gapFillQuizzes; // These quizzes are for vocabs

    public void addVocab(Vocab vocab) {
        if (vocabs == null) {
            vocabs = new ArrayList<>();
        }
        vocabs.add(vocab);

        vocab.setListeningLesson(this);
    }

    public void removeVocab(Vocab vocab) {
        if (vocabs == null) {
            throw new ResourceNotFoundException("vocab", "id", vocab.getId());
        }
        vocabs.remove(vocab);
        vocab.setListeningLesson(null);
    }

    public void addMultipleChoiceQuiz(MultipleChoiceQuiz multipleChoiceQuiz) {
        if (multipleChoiceQuizzes == null) {
            multipleChoiceQuizzes = new ArrayList<>();
        }
        multipleChoiceQuizzes.add(multipleChoiceQuiz);

        multipleChoiceQuiz.setListeningLesson(this);
    }

    public void removeMultipleChoiceQuiz(MultipleChoiceQuiz multipleChoiceQuiz) {
        if (multipleChoiceQuizzes == null) {
            throw new ResourceNotFoundException("multipleChoiceQuiz", "id", multipleChoiceQuiz.getId());
        }
        multipleChoiceQuizzes.remove(multipleChoiceQuiz);
        multipleChoiceQuiz.setListeningLesson(null);
    }

    public void addGapFillQuiz(GapFillQuiz gapFillQuiz) {
        if (gapFillQuizzes == null) {
            gapFillQuizzes = new ArrayList<>();
        }
        gapFillQuizzes.add(gapFillQuiz);

        gapFillQuiz.setListeningLesson(this);
    }

    public void removeGapFillQuiz(GapFillQuiz gapFillQuiz) {
        if (gapFillQuizzes == null) {
            throw new ResourceNotFoundException("gapFillQuiz", "id", gapFillQuiz.getId());
        }
        gapFillQuizzes.remove(gapFillQuiz);
        gapFillQuiz.setListeningLesson(null);
    }
}
