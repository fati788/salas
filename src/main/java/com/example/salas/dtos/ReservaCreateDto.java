package com.example.salas.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaCreateDto(LocalDate fecha,
                               LocalTime horaInicio,
                               LocalTime horaFin,
                               String observaciones,
                               String comentario,
                               Long salaId,
                               Long usuarioId,
                               Long departamentoId) {
}
