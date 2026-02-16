package com.example.salas.dtos;

import java.util.List;

public record UsuarioDto(Long id,
                         String nombre,
                         Integer tipoAcceso,
                         List<ReservaResumenDto> reservas) {
}
