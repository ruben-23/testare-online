package org.example.testareonline.service;

import org.example.testareonline.dto.request.CreateTestRequest;
import org.example.testareonline.dto.request.SubmitTestRequest;
import org.example.testareonline.dto.request.TestRequest;
import org.example.testareonline.dto.response.TestDTO;
import org.example.testareonline.dto.response.TestFullDTO;
import org.example.testareonline.dto.response.TestResultDTO;

import java.util.List;

public interface TestService {
    TestDTO createTest(TestRequest request);
    TestDTO createFullTest(CreateTestRequest request);
    TestDTO getTestById(Integer id);
    TestFullDTO getFullTestById(Integer id);
    List<TestDTO> getAllTeste();
    TestDTO updateTest(Integer id, TestRequest request);
    TestDTO updateFullTest(Integer testId, CreateTestRequest request);
    void deleteTest(Integer id);
    TestFullDTO takeTest(Integer id);
    TestResultDTO submitTest(Integer testId, SubmitTestRequest request);
}