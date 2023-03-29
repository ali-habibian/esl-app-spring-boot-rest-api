package com.habibian.repository;

import com.habibian.domain.entity.Vocab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocabRepository extends JpaRepository<Vocab, Long> {
    List<Vocab> findAllByListeningLesson_Id(long lessonId);
}
