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
public class SubmitTestRequest {
    private Integer testId;
    private List<AnswerSubmission> answers;
}