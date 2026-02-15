package com.example.salas.mappers;

import com.example.salas.dtos.UsuarioCreateDto;
import com.example.salas.dtos.UsuarioDto;
import com.example.salas.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDto toDto(Usuario user);
    Usuario toEntity(UsuarioCreateDto userDto);
}
