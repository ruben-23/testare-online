package org.example.testareonline.dto.request;

import java.util.List;

public record CreateIntrebareRequest(
        String continut,
        List<CreateOptiuneRequest> optiuni
) {}