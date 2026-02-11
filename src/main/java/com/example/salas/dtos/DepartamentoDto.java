package com.example.salas.dtos;

import java.util.List;

public record DepartamentoDto(String descripcion , List<SalaResumenDto> salas) {
}
