package com.habibian.dto.gap_fill_quiz;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class GapFillQuizRequestDTO {
    private String question;
    private String answer;
    private long lessonId;
}
