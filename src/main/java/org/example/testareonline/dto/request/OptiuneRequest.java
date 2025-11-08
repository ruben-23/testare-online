package org.example.testareonline.dto.request;

public record OptiuneRequest(
        String continut,
        Integer punctaj,
        Boolean isCorrect,
        Integer idIntrebare
) {}