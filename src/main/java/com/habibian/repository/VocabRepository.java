package com.habibian.repository;

import com.habibian.domain.entity.Vocab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabRepository extends JpaRepository<Vocab, Long> {
}
