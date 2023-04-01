/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.dto.gap_fill_quiz;

import com.habibian.domain.entity.ListeningLesson;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class GapFillQuizResponseDTO {
    private long id;
    private String question;
    private String answer;
    private ListeningLesson listeningLesson;
}
