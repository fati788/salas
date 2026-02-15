package com.example.salas.services;

import com.example.salas.dtos.ReservaCreateDto;
import com.example.salas.dtos.ReservaDto;

import java.util.List;
import java.util.Optional;

public interface ReservaService {

    public ReservaDto create(ReservaCreateDto dto);
    public List<ReservaDto> findAll();
    public Optional<ReservaDto> findById(Long id);
    public boolean delete(Long id);
    List<ReservaDto> findByFecha(String fecha);
    public ReservaDto update(Long id, ReservaCreateDto dto);

}
