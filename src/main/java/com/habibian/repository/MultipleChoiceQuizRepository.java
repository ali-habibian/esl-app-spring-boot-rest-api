package com.habibian.repository;

import com.habibian.domain.entity.MultipleChoiceQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultipleChoiceQuizRepository extends JpaRepository<MultipleChoiceQuiz, Long> {
    List<MultipleChoiceQuiz> findAllByListeningLesson_Id(long lessonId);

    Page<MultipleChoiceQuiz> findAllByListeningLesson_Id(long lessonId, Pageable pageable);
}
