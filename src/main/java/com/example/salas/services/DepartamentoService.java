package com.example.salas.services;

import com.example.salas.dtos.DepartamentoCreateDto;
import com.example.salas.dtos.DepartamentoDto;
import com.example.salas.dtos.ReservaCreateDto;
import com.example.salas.dtos.ReservaDto;

import java.util.List;
import java.util.Optional;

public interface DepartamentoService {
    public DepartamentoDto create(DepartamentoCreateDto dto);


    List<DepartamentoDto> findAll();

    Optional<DepartamentoDto> findById(Long id);

    DepartamentoDto update(Long id, DepartamentoCreateDto dto);

    boolean delete(Long id);
}
