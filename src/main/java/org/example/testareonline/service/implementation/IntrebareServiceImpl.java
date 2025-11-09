package org.example.testareonline.service.implementation;

import lombok.AllArgsConstructor;
import org.example.testareonline.dto.request.IntrebareRequest;
import org.example.testareonline.dto.response.IntrebareDTO;
import org.example.testareonline.entity.Intrebare;
import org.example.testareonline.entity.Test;
import org.example.testareonline.mapper.IntrebareMapper;
import org.example.testareonline.repository.IntrebareRepository;
import org.example.testareonline.repository.TestRepository;
import org.example.testareonline.service.IntrebareService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class IntrebareServiceImpl implements IntrebareService {

    private final IntrebareRepository intrebareRepository;
    private final TestRepository testRepository;
    private final IntrebareMapper intrebareMapper;

    @Override
    public IntrebareDTO createIntrebare(IntrebareRequest request) {
        Intrebare intrebare = intrebareMapper.toEntity(request);
        Test test = testRepository.findById(request.idTest())
                .orElseThrow(() -> new RuntimeException("Test not found"));
        intrebare.setTest(test);

        return intrebareMapper.toDTO(intrebareRepository.save(intrebare));
    }

    @Override
    public IntrebareDTO getIntrebareById(Integer id) {
        return intrebareRepository.findById(id)
                .map(intrebareMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Intrebare not found"));
    }

    @Override
    public List<IntrebareDTO> getAllIntrebari() {
        return intrebareRepository.findAll().stream()
                .map(intrebareMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public IntrebareDTO updateIntrebare(Integer id, IntrebareRequest request) {
        Intrebare intrebare = intrebareRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intrebare not found"));

        intrebare.setContinut(request.continut());

        Test test = testRepository.findById(request.idTest())
                .orElseThrow(() -> new RuntimeException("Test not found"));
        intrebare.setTest(test);

        return intrebareMapper.toDTO(intrebareRepository.save(intrebare));
    }

    @Override
    public void deleteIntrebare(Integer id) {
        intrebareRepository.deleteById(id);
    }
}
