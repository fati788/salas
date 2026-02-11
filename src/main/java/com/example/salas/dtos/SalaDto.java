package com.example.salas.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record SalaDto( Long id,
                       String descripcion,
                       Integer orden,
                       Boolean obsoleta,
                       Boolean oculta ,
                       DepartamentoDto departament,
                       List<ReservaResumenDto> reservas) {
}