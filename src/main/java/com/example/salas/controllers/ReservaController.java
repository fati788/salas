package com.example.salas.controllers;

import com.example.salas.dtos.ReservaCreateDto;
import com.example.salas.dtos.ReservaDto;
import com.example.salas.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaDto> saveReserva(@RequestBody ReservaCreateDto reserva){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.create(reserva));
    }
    @GetMapping
    public ResponseEntity<List<ReservaDto>> findAll(){
        return ResponseEntity.ok(reservaService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> getReserva(@PathVariable Long id){
        return reservaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReserva(@PathVariable Long id){
        boolean encontrado = reservaService.delete(id);
        if (encontrado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Buscar por fecha:
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<ReservaDto>> reservasPorFecha(
            @PathVariable String fecha
    ){
        return ResponseEntity.ok(reservaService.findByFecha(fecha));
    }


}
