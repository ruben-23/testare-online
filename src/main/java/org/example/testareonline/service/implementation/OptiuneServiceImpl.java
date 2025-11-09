package org.example.testareonline.service.implementation;

import lombok.AllArgsConstructor;
import org.example.testareonline.dto.request.OptiuneRequest;
import org.example.testareonline.dto.response.OptiuneDTO;
import org.example.testareonline.entity.Intrebare;
import org.example.testareonline.entity.Optiune;
import org.example.testareonline.entity.Test;
import org.example.testareonline.mapper.OptiuneMapper;
import org.example.testareonline.repository.IntrebareRepository;
import org.example.testareonline.repository.OptiuneRepository;
import org.example.testareonline.service.OptiuneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OptiuneServiceImpl implements OptiuneService {

    private final OptiuneRepository optiuneRepository;
    private final IntrebareRepository intrebareRepository;
    private final OptiuneMapper optiuneMapper;

    @Override
    public OptiuneDTO createOptiune(OptiuneRequest request) {
        Optiune optiune = optiuneMapper.toEntity(request);
        Intrebare intrebare = intrebareRepository.findById(request.idIntrebare())
                .orElseThrow(() -> new RuntimeException("Test not found"));
        optiune.setIntrebare(intrebare);
        return optiuneMapper.toDTO(optiuneRepository.save(optiune));
    }

    @Override
    public OptiuneDTO getOptiuneById(Integer id) {
        return optiuneRepository.findById(id)
                .map(optiuneMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Optiune not found"));
    }

    @Override
    public List<OptiuneDTO> getAllOptiuni() {
        return optiuneRepository.findAll().stream()
                .map(optiuneMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OptiuneDTO updateOptiune(Integer id, OptiuneRequest request) {
        Optiune optiune = optiuneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Optiune not found"));

        optiune.setContinut(request.continut());
        optiune.setPunctaj(request.punctaj());
        optiune.setIsCorrect(request.isCorrect());

        return optiuneMapper.toDTO(optiuneRepository.save(optiune));
    }

    @Override
    public void deleteOptiune(Integer id) {
        optiuneRepository.deleteById(id);
    }
}