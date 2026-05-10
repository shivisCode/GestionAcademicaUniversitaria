package edu.uce.ec.matriculacion.dto;

import lombok.Data;

@Data
public class DtoComprobanteConsulta {

    private Long matriculaId;

    private Long estudianteId;

    private Long materiaId;

    private String tipoComprobante;

    private String Materia;

    private String codigoMateria;

}