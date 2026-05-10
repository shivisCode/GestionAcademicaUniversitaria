package edu.uce.ec.comprobante.entidad;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "comprobantes")
@Data
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matriculaId;

    private Long estudianteId;

    private Long materiaId;

    private String codigoComprobante;

    private String tipoComprobante;

    private LocalDateTime fechaEmision;

    private Boolean estado;
}