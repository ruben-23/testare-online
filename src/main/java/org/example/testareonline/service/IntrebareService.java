package org.example.testareonline.service;

import org.example.testareonline.dto.request.IntrebareRequest;
import org.example.testareonline.dto.response.IntrebareDTO;

import java.util.List;

public interface IntrebareService {
    IntrebareDTO createIntrebare(IntrebareRequest request);
    IntrebareDTO getIntrebareById(Integer id);
    List<IntrebareDTO> getAllIntrebari();
    IntrebareDTO updateIntrebare(Integer id, IntrebareRequest request);
    void deleteIntrebare(Integer id);
}