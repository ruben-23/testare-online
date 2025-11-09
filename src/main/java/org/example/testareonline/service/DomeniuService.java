package org.example.testareonline.service;

import org.example.testareonline.dto.request.DomeniuRequest;
import org.example.testareonline.dto.response.DomeniuDTO;

import java.util.List;

public interface DomeniuService {
    DomeniuDTO createDomeniu(DomeniuRequest request);
    DomeniuDTO getDomeniuById(Integer id);
    List<DomeniuDTO> getAllDomenii();
    DomeniuDTO updateDomeniu(Integer id, DomeniuRequest request);
    void deleteDomeniu(Integer id);
}