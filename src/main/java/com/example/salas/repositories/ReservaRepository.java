package com.example.salas.repositories;

import com.example.salas.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva , Long> {
    List<Reserva> findByFecha(LocalDate fecha);

    @Query("""
SELECT r FROM reservas r
WHERE r.sala.id = :salaId
AND r.fecha = :fecha
AND r.baja = false
AND (
    :horaInicio < r.horaFin
    AND :horaFin > r.horaInicio
)
""")
    List<Reserva> findReservasSolapadas(
            @Param("salaId") Long salaId,
            @Param("fecha") LocalDate fecha,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFin") LocalTime horaFin
    );


}
