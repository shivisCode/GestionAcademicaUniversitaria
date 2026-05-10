package edu.uce.ec.comprobante.dto;

import lombok.Data;

@Data
public class DtoComprobanteConsulta {

    private Long matriculaId;

    private Long estudianteId;

    private Long materiaId;

    private String tipoComprobante;

    private String nombreEstudiante;

    private String Materia;

}
