package edu.uce.ec.comprobante.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DtoComprobanteRespuesta {

    private Long id;

    private Long matriculaId;

    private Long estudianteId;

    private Long materiaId;

    private String codigoComprobante;

    private String tipoComprobante;

    private LocalDateTime fechaEmision;

    private Boolean estado;
}