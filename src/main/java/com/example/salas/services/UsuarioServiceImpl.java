package com.example.salas.services;

import com.example.salas.dtos.UsuarioCreateDto;
import com.example.salas.dtos.UsuarioDto;
import com.example.salas.entities.Usuario;
import com.example.salas.mappers.UsuarioMapper;
import com.example.salas.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioMapper mapper;
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioMapper mapper,
                              UsuarioRepository usuarioRepository) {
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
    }

    //  CREATE
    @Override
    @Transactional
    public UsuarioDto create(UsuarioCreateDto dto) {
        Usuario usuario = mapper.toEntity(dto);
        return mapper.toDto(usuarioRepository.save(usuario));
    }

    //  READ ALL
    @Override
    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    // READ BY ID
    @Override
    public Optional<UsuarioDto> findById(Long id) {
        return usuarioRepository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public UsuarioDto update(Long id, UsuarioCreateDto dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Usuario no encontrado"));

        usuario.setNombre(dto.nombre());
        usuario.setTipoAcceso(dto.tipoAcceso());

        return mapper.toDto(usuarioRepository.save(usuario));
    }

    // DELETE
    @Override
    @Transactional
    public boolean delete(Long id) {

        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
