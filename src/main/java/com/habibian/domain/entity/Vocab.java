/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.domain.entity;

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
public class Vocab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String title;
    private String SentenceInLesson;
    private String description;

    @OneToMany(mappedBy = "vocab", cascade = CascadeType.REMOVE)
    private List<ExampleSentence> exampleSentences;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "listening_lesson_id")
    private ListeningLesson listeningLesson;

    public void addExampleSentence(ExampleSentence sentence) {
        if (exampleSentences == null) {
            exampleSentences = new ArrayList<>();
        }
        exampleSentences.add(sentence);

        sentence.setVocab(this);
    }

    public void removeExampleSentence(ExampleSentence sentence) {
        if (exampleSentences == null) {
            throw new ResourceNotFoundException("ExampleSentence", "id", sentence.getId());
        }
        exampleSentences.remove(sentence);
        sentence.setVocab(null);
    }
}
