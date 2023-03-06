package com.habibian.domain.entity;

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
public class Vocab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String title;
    private String SentenceInLesson;
    private String description;

    @OneToMany
    private List<ExampleSentence> exampleSentences;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}