package com.example.salas.dtos;

import java.util.List;

public record UsuarioDto(Long id,
                         String nombre,
                         Integer TipoAcceso,
                         List<ReservaResumenDto> reservas) {
}
