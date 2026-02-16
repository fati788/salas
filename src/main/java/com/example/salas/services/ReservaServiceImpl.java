package com.example.salas.services;

import com.example.salas.dtos.ReservaCreateDto;
import com.example.salas.dtos.ReservaDto;
import com.example.salas.entities.Departamento;
import com.example.salas.entities.Reserva;
import com.example.salas.entities.Sala;
import com.example.salas.entities.Usuario;
import com.example.salas.mappers.ReservaMapper;
import com.example.salas.repositories.DepartamentoRepository;
import com.example.salas.repositories.ReservaRepository;
import com.example.salas.repositories.SalaRepository;
import com.example.salas.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ReservaMapper reservaMapper;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public ReservaDto create(ReservaCreateDto dto) {


        // Buscar el departamento
        Departamento departamento = departamentoRepository.findById(dto.departamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Departamento no encontrado con id: " + dto.departamentoId()));
        // Obtener sala
        Sala sala = salaRepository.findById(dto.salaId())
                .orElseThrow(() -> new EntityNotFoundException("Sala no encontrada"));

        // Obtener usuario
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        List<Reserva> solapadas = reservaRepository.findReservasSolapadas(
                dto.salaId(),
                dto.fecha(),
                dto.horaInicio(),
                dto.horaFin()
        );

        if (!solapadas.isEmpty()) {
            throw new IllegalStateException("La sala ya está ocupada en ese horario");
        }

        // Crear reserva
        Reserva reserva = new Reserva();
        reserva.setSala(sala);
        reserva.setUsuario(usuario);
        reserva.setDepartamento(departamento);  // ⚡ Muy importante
        reserva.setFecha(dto.fecha());
        reserva.setHoraInicio(dto.horaInicio());
        reserva.setHoraFin(dto.horaFin());
        reserva.setObservaciones(dto.observaciones());
        reserva.setComentario(dto.comentario());
        reserva.setBaja(false);
        reserva.setFechaCre(LocalDateTime.now());

        reserva = reservaRepository.save(reserva);

        return reservaMapper.toDto(reserva);
    }


    @Override
    public List<ReservaDto> findAll() {
        return reservaRepository.findAll().stream().map(reservaMapper::toDto).toList();
    }

    @Override
    public Optional<ReservaDto> findById(Long id) {
        return reservaRepository.findById(id).map(reservaMapper::toDto);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()){
            reservaRepository.delete(reserva.get());
            return  true;
        }else {
            return  false;
        }
    }

    @Override
    public List<ReservaDto> findByFecha(String fecha) {
        // Convertir String a LocalDate
        LocalDate date = LocalDate.parse(fecha);

        // Buscar reservas de esa fecha
        List<Reserva> reservas = reservaRepository.findByFecha(date);

        // Convertir entidades a DTOs
        return reservas.stream()
                .map(reservaMapper::toDto)
                .toList();
    }

    @Override
    public ReservaDto update(Long id, ReservaCreateDto dto) {

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));

        // Obtener sala
        Sala sala = salaRepository.findById(dto.salaId())
                .orElseThrow(() -> new EntityNotFoundException("Sala no encontrada"));

        // Obtener usuario
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        // Comprobar solapamiento EXCLUYENDO la propia reserva
        List<Reserva> solapadas = reservaRepository.findReservasSolapadas(
                        dto.salaId(),
                        dto.fecha(),
                        dto.horaInicio(),
                        dto.horaFin()
                ).stream()
                .filter(r -> !r.getId().equals(id)) //
                .toList();

        if (!solapadas.isEmpty()) {
            throw new IllegalStateException("La sala ya está ocupada en ese horario");
        }

        // Actualizar campos
        reserva.setSala(sala);
        reserva.setUsuario(usuario);
        reserva.setFecha(dto.fecha());
        reserva.setHoraInicio(dto.horaInicio());
        reserva.setHoraFin(dto.horaFin());
        reserva.setObservaciones(dto.observaciones());
        reserva.setComentario(dto.comentario());
        reserva.setFechaMod(LocalDateTime.now());
        reserva = reservaRepository.save(reserva);

        return reservaMapper.toDto(reserva);
    }

}
