package com.example.salas.dtos;

public record SalaCreatetoDto(         String descripcion,
                                       Integer orden,
                                       Boolean obsoleta,
                                       Boolean oculta) {
}
