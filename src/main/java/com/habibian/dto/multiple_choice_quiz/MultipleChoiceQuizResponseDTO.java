/*
 * Copyright (c) 2023 Ali Habibian. All rights reserved.
 *
 */

package com.habibian.dto.multiple_choice_quiz;

import com.habibian.domain.entity.ListeningLesson;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MultipleChoiceQuizResponseDTO {
    private long id;
    private String question;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private String answer;
    private ListeningLesson listeningLesson;
}
