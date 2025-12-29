package org.example.testareonline.controller;

import lombok.AllArgsConstructor;
import org.example.testareonline.dto.request.CreateTestRequest;
import org.example.testareonline.dto.request.SubmitTestRequest;
import org.example.testareonline.dto.request.TestRequest;
import org.example.testareonline.dto.response.TestDTO;
import org.example.testareonline.dto.response.TestFullDTO;
import org.example.testareonline.dto.response.TestResultDTO;
import org.example.testareonline.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teste")
@AllArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping
    public ResponseEntity<TestDTO> createTest(@RequestBody TestRequest request) {
        return ResponseEntity.ok(testService.createTest(request));
    }

    @PostMapping("/create/full")
    public ResponseEntity<TestDTO> createTest(@RequestBody CreateTestRequest request) {
        return ResponseEntity.ok(testService.createFullTest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDTO> getTestById(@PathVariable Integer id) {
        return ResponseEntity.ok(testService.getTestById(id));
    }

    @GetMapping
    public ResponseEntity<List<TestDTO>> getAllTeste() {
        return ResponseEntity.ok(testService.getAllTeste());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestDTO> updateTest(@PathVariable Integer id, @RequestBody TestRequest request) {
        return ResponseEntity.ok(testService.updateTest(id, request));
    }

    @PutMapping("/{id}/full")
    public ResponseEntity<TestDTO> updateFullTest(@PathVariable Integer id, @RequestBody CreateTestRequest request) {
        return ResponseEntity.ok(testService.updateFullTest(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Integer id) {
        testService.deleteTest(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/take")
    public ResponseEntity<TestFullDTO> takeTest(@PathVariable Integer id) {
        TestFullDTO test = testService.takeTest(id);
        return ResponseEntity.ok(test);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<TestFullDTO> getTestInfo(@PathVariable Integer id) {
        TestFullDTO test = testService.getFullTestById(id);
        return ResponseEntity.ok(test);
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<TestResultDTO> submitTest(
            @PathVariable Integer id,
            @RequestBody SubmitTestRequest request) {

        TestResultDTO result = testService.submitTest(id, request);
        return ResponseEntity.ok(result);
    }
}