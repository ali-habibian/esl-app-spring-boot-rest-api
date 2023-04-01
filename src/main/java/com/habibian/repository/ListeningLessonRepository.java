/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.repository;

import com.habibian.domain.entity.ListeningLesson;
import com.habibian.enumeration.LessonLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListeningLessonRepository extends JpaRepository<ListeningLesson, Long> {
    List<ListeningLesson> findAllByLevel(LessonLevel level);

    Page<ListeningLesson> findAllByLevel(LessonLevel level, Pageable pageable);
}
