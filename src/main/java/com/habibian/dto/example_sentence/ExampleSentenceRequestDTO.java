package com.habibian.dto.example_sentence;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ExampleSentenceRequestDTO {
    private String sentence;
    private long vocabId;
}
