package com.example.salas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "reservas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
 @Setter
@ToString
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String observaciones;
    private String comentario;
    private Boolean baja = false;
    private LocalDateTime fechaCre;

    @ManyToOne(fetch = FetchType.LAZY)
    //, nullable = false
    @JoinColumn(name = "departamento_id" , nullable = false)
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "usuario_id" , nullable = false)
    private Usuario usuario;


}
