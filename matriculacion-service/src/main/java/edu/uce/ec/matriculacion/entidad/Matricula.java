package edu.uce.ec.matriculacion.entidad;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "matriculas")
@Data
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long estudianteId;

    private Long materiaId;

    private LocalDateTime fechaMatricula;

    private Boolean estado;
}