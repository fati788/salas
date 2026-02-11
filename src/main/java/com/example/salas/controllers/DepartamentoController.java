package com.example.salas.controllers;

import com.example.salas.dtos.DepartamentoCreateDto;
import com.example.salas.dtos.DepartamentoDto;
import com.example.salas.services.DepartamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@CrossOrigin(origins = "http://localhost:3000")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @PostMapping
    public ResponseEntity<DepartamentoDto> create(@RequestBody DepartamentoCreateDto dto) {
        return ResponseEntity.ok(departamentoService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoDto>> findAll() {
        return ResponseEntity.ok(departamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDto> findById(@PathVariable Long id) {
        return departamentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDto> update(
            @PathVariable Long id,
            @RequestBody DepartamentoCreateDto dto) {

        return ResponseEntity.ok(departamentoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean deleted = departamentoService.delete(id);

        if (deleted)
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }
}
