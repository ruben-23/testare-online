package org.example.testareonline.dto.request;

public record CreateOptiuneRequest(
        String continut,
        Integer punctaj,
        Boolean isCorrect
) {}