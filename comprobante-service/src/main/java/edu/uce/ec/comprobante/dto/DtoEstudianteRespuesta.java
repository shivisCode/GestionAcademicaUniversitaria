package edu.uce.ec.comprobante.dto;

import lombok.Data;

@Data
public class DtoEstudianteRespuesta {
    
    private Long id;

    private String nombre;

    private String cedula;

    private String carrera;

    private Boolean estado;

    private String correo;
}
