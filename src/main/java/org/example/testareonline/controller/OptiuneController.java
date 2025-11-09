package org.example.testareonline.controller;

import org.example.testareonline.dto.request.OptiuneRequest;
import org.example.testareonline.dto.response.OptiuneDTO;
import org.example.testareonline.service.OptiuneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/optiuni")
public class OptiuneController {

    private final OptiuneService optiuneService;

    public OptiuneController(OptiuneService optiuneService) {
        this.optiuneService = optiuneService;
    }

    @PostMapping
    public ResponseEntity<OptiuneDTO> createOptiune(@RequestBody OptiuneRequest request) {
        return ResponseEntity.ok(optiuneService.createOptiune(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptiuneDTO> getOptiuneById(@PathVariable Integer id) {
        return ResponseEntity.ok(optiuneService.getOptiuneById(id));
    }

    @GetMapping
    public ResponseEntity<List<OptiuneDTO>> getAllOptiuni() {
        return ResponseEntity.ok(optiuneService.getAllOptiuni());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OptiuneDTO> updateOptiune(@PathVariable Integer id, @RequestBody OptiuneRequest request) {
        return ResponseEntity.ok(optiuneService.updateOptiune(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOptiune(@PathVariable Integer id) {
        optiuneService.deleteOptiune(id);
        return ResponseEntity.noContent().build();
    }
}