package edu.uce.ec.estudiante.dto;

import lombok.Data;

@Data
public class DtoEstudianteRespuesta {
    private Long id;
    private String nombre;
    private String cedula;
    private String carrera;
    private String correo;
    private Boolean estado;
}
