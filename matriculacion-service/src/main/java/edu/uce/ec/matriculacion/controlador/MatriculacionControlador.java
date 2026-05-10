package edu.uce.ec.matriculacion.controlador;

import edu.uce.ec.matriculacion.dto.DtoMatriculaConsulta;
import edu.uce.ec.matriculacion.dto.DtoMatriculaRespuesta;
import edu.uce.ec.matriculacion.servicio.MatriculacionServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculacionControlador {

    private final MatriculacionServicio matriculacionServicio;

    public MatriculacionControlador(MatriculacionServicio matriculacionServicio) {
        this.matriculacionServicio = matriculacionServicio;
    }

    @GetMapping("/health")
    public String health() {
        return "Microservicio matriculacion-service operativo";
    }

    // Registrar matrícula validando cupos
    @PostMapping("/registrarMatricula")
    public DtoMatriculaRespuesta registrarMatricula(
            @RequestBody DtoMatriculaConsulta dto) {

        return matriculacionServicio.registrarMatricula(dto);
    }

    // Listar matrículas
    @GetMapping("/listarMatriculas")
    public List<DtoMatriculaRespuesta> listarMatriculas() {

        return matriculacionServicio.listarMatriculas();
    }

    // Obtener matrícula por ID
    @GetMapping("/obtenerMatricula")
    public DtoMatriculaRespuesta obtenerMatricula(
            @RequestParam Long id) {

        return matriculacionServicio.obtenerMatricula(id);
    }

    // Anular matrícula
    @PutMapping("/anularMatricula")
    public DtoMatriculaRespuesta anularMatricula(
            @RequestParam Long id) {

        return matriculacionServicio.anularMatricula(id);
    }
}