package edu.uce.ec.materia.entidad;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "materias")
@Data
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String codigo;

    private Integer creditos;
    
    private Integer cupoTotal;

    private Integer cupoDisponible;

    private Boolean estado;

    private LocalDateTime fechaRegistro;
}