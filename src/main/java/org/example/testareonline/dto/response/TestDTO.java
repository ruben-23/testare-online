package org.example.testareonline.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TestDTO {
    private Integer id;
    private String titlu;
    private LocalDateTime dataCrearii;
    private Integer idUser;
    private Integer idDomeniu;

}