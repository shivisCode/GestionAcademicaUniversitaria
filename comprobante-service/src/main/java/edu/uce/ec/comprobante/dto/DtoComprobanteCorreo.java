package edu.uce.ec.comprobante.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DtoComprobanteCorreo {

    private Long matriculaId;

    private String nombreEstudiante;

    private String materia;

    private String codigoComprobante;

    private String tipoComprobante;

    private LocalDateTime fechaEmision;

    private Boolean estado;
}
