package com.example.salas.services;


import com.example.salas.dtos.SalaCreatetoDto;
import com.example.salas.dtos.SalaDto;

public interface SalaService {
     public SalaDto create(SalaCreatetoDto dto);
     //SalaDto update(Long id, SalaCreatetoDto dto);
     boolean delete(Long id);
}
