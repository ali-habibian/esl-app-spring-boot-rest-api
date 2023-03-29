package com.habibian.dto.example_sentence;

import com.habibian.domain.entity.Vocab;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ExampleSentenceResponseDTO {
    private Long id;
    private String sentence;
    private Vocab vocab;
}
