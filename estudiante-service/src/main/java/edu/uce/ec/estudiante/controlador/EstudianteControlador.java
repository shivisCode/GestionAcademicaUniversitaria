package edu.uce.ec.estudiante.controlador;

import edu.uce.ec.estudiante.dto.DtoEstudianteConsulta;
import edu.uce.ec.estudiante.dto.DtoEstudianteRespuesta;
import edu.uce.ec.estudiante.servicio.EstudianteServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteControlador {

    private final EstudianteServicio estudianteServicio;

    public EstudianteControlador(EstudianteServicio estudianteServicio) {
        this.estudianteServicio = estudianteServicio;
    }

    @GetMapping("/health")
    public String health() {
        return "Microservicio estudiante-service operativo";
    }

    @PostMapping("/guardarEstudiante")
    public DtoEstudianteRespuesta guardarEstudiante(@RequestBody DtoEstudianteConsulta dto) {
        return estudianteServicio.guardarEstudiante(dto);
    }

    @GetMapping("/listarEstudiantes")
    public List<DtoEstudianteRespuesta> listarEstudiantes() {
        return estudianteServicio.listarEstudiantes();
    }

    @GetMapping("/obtenerEstudiante")
    public DtoEstudianteRespuesta obtenerEstudiante(@RequestParam Long id) {
        return estudianteServicio.obtenerEstudiante(id);
    }

    @PutMapping("/actualizarEstudiante")
    public DtoEstudianteRespuesta actualizarEstudiante(
            @RequestParam Long id,
            @RequestBody DtoEstudianteConsulta dto) {
        return estudianteServicio.actualizarEstudiante(id, dto);
    }

    @DeleteMapping("/eliminarEstudiante")
    public String eliminarEstudiante(@RequestParam Long id) {
        estudianteServicio.eliminarEstudiante(id);
        return "Estudiante eliminado correctamente";
    }
}
