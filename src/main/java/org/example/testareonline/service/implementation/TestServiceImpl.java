package org.example.testareonline.service.implementation;

import lombok.AllArgsConstructor;
import org.example.testareonline.dto.request.TestRequest;
import org.example.testareonline.dto.response.TestDTO;
import org.example.testareonline.entity.Domeniu;
import org.example.testareonline.entity.Test;
import org.example.testareonline.entity.User;
import org.example.testareonline.mapper.TestMapper;
import org.example.testareonline.repository.DomeniuRepository;
import org.example.testareonline.repository.TestRepository;
import org.example.testareonline.repository.UserRepository;
import org.example.testareonline.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final UserRepository userRepository;
    private final DomeniuRepository domeniuRepository;
    private final TestMapper testMapper;

    @Override
    public TestDTO createTest(TestRequest request) {
        Test test = testMapper.toEntity(request);
        User user = userRepository.findById(request.idUser())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Domeniu domeniu = domeniuRepository.findById(request.idDomeniu())
                .orElseThrow(() -> new RuntimeException("Domeniu not found"));

        test.setUser(user);
        test.setDomeniu(domeniu);

        return testMapper.toDTO(testRepository.save(test));
    }

    @Override
    public TestDTO getTestById(Integer id) {
        return testRepository.findById(id)
                .map(testMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Test not found"));
    }

    @Override
    public List<TestDTO> getAllTeste() {
        return testRepository.findAll().stream()
                .map(testMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TestDTO updateTest(Integer id, TestRequest request) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        test.setTitlu(request.titlu());

        User user = userRepository.findById(request.idUser())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Domeniu domeniu = domeniuRepository.findById(request.idDomeniu())
                .orElseThrow(() -> new RuntimeException("Domeniu not found"));

        test.setUser(user);
        test.setDomeniu(domeniu);

        return testMapper.toDTO(testRepository.save(test));
    }

    @Override
    public void deleteTest(Integer id) {
        testRepository.deleteById(id);
    }
}