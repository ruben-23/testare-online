package org.example.testareonline.controller;

import org.example.testareonline.dto.request.DomeniuRequest;
import org.example.testareonline.dto.response.DomeniuDTO;
import org.example.testareonline.service.DomeniuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/domenii")
public class DomeniuController {

    private final DomeniuService domeniuService;

    public DomeniuController(DomeniuService domeniuService) {
        this.domeniuService = domeniuService;
    }

    @PostMapping
    public ResponseEntity<DomeniuDTO> createDomeniu(@RequestBody DomeniuRequest request) {
        return ResponseEntity.ok(domeniuService.createDomeniu(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DomeniuDTO> getDomeniuById(@PathVariable Integer id) {
        return ResponseEntity.ok(domeniuService.getDomeniuById(id));
    }

    @GetMapping
    public ResponseEntity<List<DomeniuDTO>> getAllDomenii() {
        return ResponseEntity.ok(domeniuService.getAllDomenii());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DomeniuDTO> updateDomeniu(@PathVariable Integer id, @RequestBody DomeniuRequest request) {
        return ResponseEntity.ok(domeniuService.updateDomeniu(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDomeniu(@PathVariable Integer id) {
        domeniuService.deleteDomeniu(id);
        return ResponseEntity.noContent().build();
    }
}