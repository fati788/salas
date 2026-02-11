package com.example.salas.mappers;

import com.example.salas.dtos.SalaCreatetoDto;
import com.example.salas.dtos.SalaDto;
import com.example.salas.entities.Sala;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalaMapper {
    SalaDto toDto(Sala sala);
    Sala toEntity(SalaCreatetoDto salaDto);
}
