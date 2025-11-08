package org.example.testareonline.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OptiuneDTO {
    private Integer id;
    private String continut;
    private Integer punctaj;
    private Boolean isCorrect;
    private Integer idIntrebare;
}