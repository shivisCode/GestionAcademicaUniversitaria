package edu.uce.ec.comprobante.controlador;

import edu.uce.ec.comprobante.dto.DtoComprobanteConsulta;
import edu.uce.ec.comprobante.dto.DtoComprobanteRespuesta;
import edu.uce.ec.comprobante.servicio.ComprobanteServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteControlador {

    private final ComprobanteServicio comprobanteServicio;

    public ComprobanteControlador(ComprobanteServicio comprobanteServicio) {
        this.comprobanteServicio = comprobanteServicio;
    }

    @GetMapping("/health")
    public String health() {
        return "Microservicio comprobante-service operativo";
    }

    // Generar comprobante
    @PostMapping("/generarComprobante")
    public DtoComprobanteRespuesta generarComprobante(
            @RequestBody DtoComprobanteConsulta dto) {

        return comprobanteServicio.generarComprobante(dto);
    }

    // Listar comprobantes
    @GetMapping("/listarComprobantes")
    public List<DtoComprobanteRespuesta> listarComprobantes() {

        return comprobanteServicio.listarComprobantes();
    }

    // Obtener comprobante
    @GetMapping("/obtenerComprobante")
    public DtoComprobanteRespuesta obtenerComprobante(
            @RequestParam Long id) {

        return comprobanteServicio.obtenerComprobante(id);
    }

    // Anular comprobante
    @PutMapping("/anularComprobante")
    public DtoComprobanteRespuesta anularComprobante(
            @RequestParam Long id) {

        return comprobanteServicio.anularComprobante(id);
    }
}