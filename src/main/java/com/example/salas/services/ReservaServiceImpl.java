package com.example.salas.services;

import com.example.salas.dtos.ReservaCreateDto;
import com.example.salas.dtos.ReservaDto;
import com.example.salas.entities.Reserva;
import com.example.salas.entities.Sala;
import com.example.salas.entities.Usuario;
import com.example.salas.mappers.ReservaMapper;
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


    @Override
    public ReservaDto create(ReservaCreateDto dto) {
        // Obtener la sala de la reserva
        Sala sala = salaRepository.findById(dto.salaId())
                .orElseThrow(() -> new EntityNotFoundException("Sala no encontrada"));

        // Obtener el usuario que hace la reserva
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        // Crear una reserva nueva
        Reserva reserva = new Reserva();

        // Asignar sala y usuario
        reserva.setSala(sala);
        reserva.setUsuario(usuario);

        // Sacar los demás campos del DTO y asignarlos
        reserva.setFecha(dto.fecha());
        reserva.setHoraInicio(dto.horaInicio());
        reserva.setHoraFin(dto.horaFin());
        reserva.setObservaciones(dto.observaciones());
        reserva.setComentario(dto.comentario());
        reserva.setBaja(false);
        reserva.setFechaCre(LocalDateTime.now());

        // Guardar la reserva en la base de datos
        reserva = reservaRepository.save(reserva);

        // Devolver la reserva creada como DTO
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
}
