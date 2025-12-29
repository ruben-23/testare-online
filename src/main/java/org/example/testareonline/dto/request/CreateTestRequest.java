package org.example.testareonline.dto.request;

import java.util.List;

public record CreateTestRequest(
        String titlu,
        Integer idUser,
        Integer idDomeniu,
        List<CreateIntrebareRequest> intrebari
) {}