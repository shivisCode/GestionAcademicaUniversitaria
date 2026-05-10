package edu.uce.ec.matriculacion.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DtoMatriculaRespuesta {

    private Long id;

    private Long estudianteId;

    private Long materiaId;

    private LocalDateTime fechaMatricula;

    private Boolean estado;
}