package com.example.salas.mappers;

import com.example.salas.dtos.SalaCreatetoDto;
import com.example.salas.dtos.SalaDto;
import com.example.salas.dtos.UsuarioCreateDto;
import com.example.salas.dtos.UsuarioDto;
import com.example.salas.entities.Sala;
import com.example.salas.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDto toDto(Usuario user);
    Usuario toEntity(UsuarioCreateDto userDto);
}
