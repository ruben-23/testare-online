package org.example.testareonline.service;

import org.example.testareonline.dto.request.TestRequest;
import org.example.testareonline.dto.response.TestDTO;

import java.util.List;

public interface TestService {
    TestDTO createTest(TestRequest request);
    TestDTO getTestById(Integer id);
    List<TestDTO> getAllTeste();
    TestDTO updateTest(Integer id, TestRequest request);
    void deleteTest(Integer id);
}