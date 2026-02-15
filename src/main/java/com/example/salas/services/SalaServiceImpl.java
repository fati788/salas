package com.example.salas.services;

import com.example.salas.dtos.SalaCreatetoDto;
import com.example.salas.dtos.SalaDto;
import com.example.salas.entities.Departamento;
import com.example.salas.entities.Sala;
import com.example.salas.mappers.SalaMapper;
import com.example.salas.repositories.DepartamentoRepository;
import com.example.salas.repositories.SalaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalaServiceImpl implements SalaService {
    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private SalaMapper salaMapper;

    @Override
    public SalaDto create(SalaCreatetoDto dto) {


        // Crear la sala y asignar campos
        Sala sala = new Sala();
        sala.setDescripcion(dto.descripcion());
        sala.setOrden(dto.orden());
        sala.setObsoleta(dto.obsoleta());
        sala.setOculta(dto.oculta());


        // Guardar la sala
        sala = salaRepository.save(sala);

        // Devolver DTO
        return salaMapper.toDto(sala);
    }

    /*@Override
    public SalaDto update(Long id, SalaCreatetoDto dto) {

        // 1️ Buscar la sala existente
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Sala no encontrada"));

        // 2 Buscar el departamento (si cambia)
        Departamento departamento = departamentoRepository.findById(dto.departamentoId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Departamento no encontrado"));

        // 3 Actualizar campos
        sala.setDescripcion(dto.descripcion());
        sala.setOrden(dto.orden());
        sala.setObsoleta(dto.obsoleta());
        sala.setOculta(dto.oculta());
        sala.setDepartamento(departamento);

        //  Guardar cambios
        sala = salaRepository.save(sala);

        // 5 Devolver DTO
        return salaMapper.toDto(sala);
    }
*/
    @Override
    public boolean delete(Long id) {
        Optional<Sala> sala = salaRepository.findById(id);
        if (sala.isPresent()){
            salaRepository.delete(sala.get());
            return true;
        }else {
            return  false;
        }
    }
}
