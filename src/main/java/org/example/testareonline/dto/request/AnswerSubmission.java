package org.example.testareonline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSubmission {
    private Integer questionId;
    private List<Integer> selectedOptionIds;
}
