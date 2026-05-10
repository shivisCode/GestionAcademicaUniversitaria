package edu.uce.ec.matriculacion.dto;

import lombok.Data;

@Data
public class DtoMateriaCuposDisponibles {

    private String nombre;

    private String codigo;
    
    private Integer cupoDisponible;
}