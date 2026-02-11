package com.example.salas.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaResumenDto(LocalDate fecha,
         LocalTime horaInicio,
         LocalTime horaFin) {
}
