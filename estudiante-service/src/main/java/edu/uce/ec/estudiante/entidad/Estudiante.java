package edu.uce.ec.estudiante.entidad;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estudiantes")
@Data
public class Estudiante {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String cedula;

    @Column(unique = true)
    private String correo;

    private String carrera;

    private LocalDateTime fechaRegistro;

    private Boolean estado;
}