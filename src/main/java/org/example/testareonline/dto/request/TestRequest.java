package org.example.testareonline.dto.request;

public record TestRequest(
        String titlu,
        Integer idUser,
        Integer idDomeniu
) {}