package edu.uce.ec.materia.dto;

import lombok.Data;

@Data
public class DtoMateriaRespuesta {

    private Long id;

    private String nombre;

    private String codigo;

    private Integer creditos;

    private Integer cupoTotal;
    
    private Integer cupoDisponible;

    private Boolean estado;
}