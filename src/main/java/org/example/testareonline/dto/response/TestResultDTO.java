package org.example.testareonline.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestResultDTO {
    private double score;
    private double totalScore;
    private int totalQuestions;
    private double percentage;
    private boolean passed;
}