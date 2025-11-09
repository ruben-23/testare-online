package org.example.testareonline.service;

import org.example.testareonline.dto.request.OptiuneRequest;
import org.example.testareonline.dto.response.OptiuneDTO;

import java.util.List;

public interface OptiuneService {
    OptiuneDTO createOptiune(OptiuneRequest request);
    OptiuneDTO getOptiuneById(Integer id);
    List<OptiuneDTO> getAllOptiuni();
    OptiuneDTO updateOptiune(Integer id, OptiuneRequest request);
    void deleteOptiune(Integer id);
}