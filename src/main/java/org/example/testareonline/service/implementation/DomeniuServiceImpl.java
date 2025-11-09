package org.example.testareonline.service.implementation;

import lombok.AllArgsConstructor;
import org.example.testareonline.dto.request.DomeniuRequest;
import org.example.testareonline.dto.response.DomeniuDTO;
import org.example.testareonline.entity.Domeniu;
import org.example.testareonline.mapper.DomeniuMapper;
import org.example.testareonline.repository.DomeniuRepository;
import org.example.testareonline.service.DomeniuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DomeniuServiceImpl implements DomeniuService {

    private final DomeniuRepository domeniuRepository;
    private final DomeniuMapper domeniuMapper;

    @Override
    public DomeniuDTO createDomeniu(DomeniuRequest request) {
        Domeniu domeniu = domeniuMapper.toEntity(request);
        return domeniuMapper.toDTO(domeniuRepository.save(domeniu));
    }

    @Override
    public DomeniuDTO getDomeniuById(Integer id) {
        return domeniuRepository.findById(id)
                .map(domeniuMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Domeniu not found"));
    }

    @Override
    public List<DomeniuDTO> getAllDomenii() {
        return domeniuRepository.findAll().stream()
                .map(domeniuMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DomeniuDTO updateDomeniu(Integer id, DomeniuRequest request) {
        Domeniu domeniu = domeniuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domeniu not found"));

        domeniu.setNume(request.nume());
        return domeniuMapper.toDTO(domeniuRepository.save(domeniu));
    }

    @Override
    public void deleteDomeniu(Integer id) {
        domeniuRepository.deleteById(id);
    }
}
