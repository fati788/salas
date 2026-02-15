package com.example.salas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name ="salas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Integer orden;
    private Boolean  obsoleta;
    private Boolean oculta;


    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;
}
