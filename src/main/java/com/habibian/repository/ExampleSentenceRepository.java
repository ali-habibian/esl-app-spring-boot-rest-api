package com.habibian.repository;

import com.habibian.domain.entity.ExampleSentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleSentenceRepository extends JpaRepository<ExampleSentence, Long> {
}
