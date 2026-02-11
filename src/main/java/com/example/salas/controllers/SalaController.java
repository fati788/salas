package com.example.salas.controllers;

import com.example.salas.dtos.SalaCreatetoDto;
import com.example.salas.dtos.SalaDto;
import com.example.salas.entities.Sala;
import com.example.salas.mappers.SalaMapper;
import com.example.salas.repositories.SalaRepository;
import com.example.salas.services.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salas")
public class SalaController {
    @Autowired
    private SalaRepository repository;

    @Autowired
    private SalaMapper mapper;
    @Autowired
    private SalaService service;

    /**
     * Metodo para obtener todos las salas
     * @return
     */
    @GetMapping
    public ResponseEntity<List<SalaDto>> getAllSalas(){
        return ResponseEntity.ok(repository.findAll()
                .stream().map(mapper::toDto).toList());
    }

    /**
     * Metodo para crear una Sala
     * @param sala
     * @return
     */

    @PostMapping
    public ResponseEntity<SalaDto> createSala(@RequestBody SalaCreatetoDto sala){
        // Usamos el servicio que maneja la lógica de asignar departamento
        SalaDto nuevaSala = service.create(sala);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSala);
    }


    /**
     * Metodo Para obtenir una sala por id
     * @param id
     * @return
     */

    @GetMapping("/{id}")
    public ResponseEntity<SalaDto> getSalaById(@PathVariable Long id){
        Optional<SalaDto> sala = repository.findById(id).map(mapper::toDto);
        return sala.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Metodo para eliminar sala por id Si Existe
     * @param id
     * @return
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSala(@PathVariable Long id){
        boolean encontrada =service.delete(id);
        if (encontrada){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
