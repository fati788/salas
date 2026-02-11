package com.example.salas.mappers;


import com.example.salas.dtos.DepartamentoCreateDto;
import com.example.salas.dtos.DepartamentoDto;
import com.example.salas.dtos.ReservaDto;
import com.example.salas.entities.Departamento;
import com.example.salas.entities.Reserva;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {
    DepartamentoDto toDto(Departamento departamento);

    Departamento toEntity(DepartamentoCreateDto reservaDto);
}
