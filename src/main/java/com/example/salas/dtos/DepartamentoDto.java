package com.example.salas.dtos;

import java.util.List;

public record DepartamentoDto(Long id , String descripcion , List<ReservaResumenDto> reservas) {
}
