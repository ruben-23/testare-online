package org.example.testareonline.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class IntrebareFullDTO {
    private Integer id;
    private String continut;
    private Integer idTest;
    private Integer punctaj;
    private List<OptiuneDTO> optiuni;
}