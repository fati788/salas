package com.example.salas.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ReservaDto(  Long id,
                           LocalDate fecha,
                           LocalTime horaInicio,
                           LocalTime horaFin,
                           String observaciones,
                           String comentario,
                           Boolean baja,
                           LocalDateTime fechaCre ,
                           SalaResumenDto sala,
                           UsuarioResumenDto usuario,
                           DepartamentoResumenDto departamento ,
                           LocalDateTime fechaMod) {
}
