package com.habibian.dto.multiple_choice_quiz;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MultipleChoiceQuizRequestDTO {
    private String question;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private String answer;
    private long lessonId;
}
