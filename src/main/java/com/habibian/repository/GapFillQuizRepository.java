package com.habibian.repository;

import com.habibian.domain.entity.GapFillQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GapFillQuizRepository extends JpaRepository<GapFillQuiz, Long> {
    List<GapFillQuiz> findAllByListeningLesson_Id(long lessonId);

    Page<GapFillQuiz> findAllByListeningLesson_Id(long lessonId, Pageable pageable);
}
