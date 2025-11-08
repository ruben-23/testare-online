package org.example.testareonline.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IntrebareDTO {
    private Integer id;
    private String continut;
    private Integer idTest;
}