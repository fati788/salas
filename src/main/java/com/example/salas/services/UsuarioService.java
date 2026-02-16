package com.example.salas.services;

import com.example.salas.dtos.LoginDto;
import com.example.salas.dtos.UsuarioCreateDto;
import com.example.salas.dtos.UsuarioDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

  UsuarioDto create(UsuarioCreateDto dto);

  List<UsuarioDto> findAll();

  Optional<UsuarioDto> findById(Long id);

  UsuarioDto update(Long id, UsuarioCreateDto dto);

  boolean delete(Long id);
  ResponseEntity<UsuarioDto> login(LoginDto usuario) ;
}
