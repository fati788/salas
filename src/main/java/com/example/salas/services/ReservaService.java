package com.example.salas.services;

import com.example.salas.dtos.ReservaCreateDto;
import com.example.salas.dtos.ReservaDto;

import java.util.List;
import java.util.Optional;

public interface ReservaService {

     ReservaDto create(ReservaCreateDto dto);
     List<ReservaDto> findAll();
     Optional<ReservaDto> findById(Long id);
     boolean delete(Long id);
    List<ReservaDto> findByFecha(String fecha);
     ReservaDto update(Long id, ReservaCreateDto dto);

}
