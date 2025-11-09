package org.example.testareonline.controller;

import org.example.testareonline.dto.request.IntrebareRequest;
import org.example.testareonline.dto.response.IntrebareDTO;
import org.example.testareonline.service.IntrebareService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intrebari")
public class IntrebareController {

    private final IntrebareService intrebareService;

    public IntrebareController(IntrebareService intrebareService) {
        this.intrebareService = intrebareService;
    }

    @PostMapping
    public ResponseEntity<IntrebareDTO> createIntrebare(@RequestBody IntrebareRequest request) {
        return ResponseEntity.ok(intrebareService.createIntrebare(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntrebareDTO> getIntrebareById(@PathVariable Integer id) {
        return ResponseEntity.ok(intrebareService.getIntrebareById(id));
    }

    @GetMapping
    public ResponseEntity<List<IntrebareDTO>> getAllIntrebari() {
        return ResponseEntity.ok(intrebareService.getAllIntrebari());
    }

    @PutMapping("/{id}")
    public ResponseEntity<IntrebareDTO> updateIntrebare(@PathVariable Integer id, @RequestBody IntrebareRequest request) {
        return ResponseEntity.ok(intrebareService.updateIntrebare(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIntrebare(@PathVariable Integer id) {
        intrebareService.deleteIntrebare(id);
        return ResponseEntity.noContent().build();
    }
}
