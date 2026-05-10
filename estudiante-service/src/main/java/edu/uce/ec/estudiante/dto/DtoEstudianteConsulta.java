package edu.uce.ec.estudiante.dto;

import lombok.Data;

@Data
public class DtoEstudianteConsulta {

    private Long id;

    private String nombre;

    private String cedula;

    private String correo;

    private String carrera;
    
    private Boolean estado;
}