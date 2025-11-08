package org.example.testareonline.dto.request;

public record UserRequest(
        String username,
        String parola,
        String rol
) {}