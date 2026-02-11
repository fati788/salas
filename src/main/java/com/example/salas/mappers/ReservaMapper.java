package com.example.salas.mappers;

import com.example.salas.dtos.ReservaCreateDto;
import com.example.salas.dtos.ReservaDto;
import com.example.salas.entities.Reserva;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    ReservaDto toDto(Reserva reserva);

    Reserva toEntity(ReservaCreateDto reservaDto);
}
