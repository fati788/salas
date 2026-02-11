package com.example.salas.services;

import com.example.salas.dtos.DepartamentoCreateDto;
import com.example.salas.dtos.DepartamentoDto;
import com.example.salas.entities.Departamento;
import com.example.salas.mappers.DepartamentoMapper;
import com.example.salas.repositories.DepartamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private DepartamentoMapper departamentoMapper;
    @Override
    @Transactional
    public DepartamentoDto create(DepartamentoCreateDto dto) {

        Departamento departamento = departamentoMapper.toEntity(dto);

        departamento = departamentoRepository.save(departamento);

        return departamentoMapper.toDto(departamento);
    }

    @Override
    public List<DepartamentoDto> findAll() {
        return departamentoRepository.findAll()
                .stream()
                .map(departamentoMapper::toDto)
                .toList();
    }

    @Override
    public Optional<DepartamentoDto> findById(Long id) {
        return departamentoRepository.findById(id)
                .map(departamentoMapper::toDto);
    }

    @Override
    public DepartamentoDto update(Long id, DepartamentoCreateDto dto) {

        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Departamento no encontrado"));

        departamento.setDescripcion(dto.descripcion());

        departamento = departamentoRepository.save(departamento);

        return departamentoMapper.toDto(departamento);
    }

    @Override
    public boolean delete(Long id) {
        if (departamentoRepository.existsById(id)) {
            departamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
