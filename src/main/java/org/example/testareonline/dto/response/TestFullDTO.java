package org.example.testareonline.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TestFullDTO {
    private Integer id;
    private String titlu;
    private LocalDateTime dataCrearii;
    private Integer idUser;
    private Integer idDomeniu;
    private List<IntrebareFullDTO> intrebari;
}