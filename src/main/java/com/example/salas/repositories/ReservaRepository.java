package com.example.salas.repositories;

import com.example.salas.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva , Long> {
    List<Reserva> findByFecha(LocalDate fecha);

}
