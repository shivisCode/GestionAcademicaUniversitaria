package edu.uce.ec.materia.controlador;

import edu.uce.ec.materia.dto.DtoMateriaConsulta;
import edu.uce.ec.materia.dto.DtoMateriaCuposDisponibles;
import edu.uce.ec.materia.dto.DtoMateriaRespuesta;
import edu.uce.ec.materia.servicio.MateriaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
public class MateriaControlador {

    private final MateriaServicio materiaServicio;

    public MateriaControlador(MateriaServicio materiaServicio) {
        this.materiaServicio = materiaServicio;
    }

    @GetMapping("/health")
    public String health() {
        return "Microservicio materia-service operativo";
    }

    @PostMapping("/guardarMateria")
    public DtoMateriaRespuesta guardarMateria(@RequestBody DtoMateriaConsulta dto) {
        return materiaServicio.guardarMateria(dto);
    }

    @GetMapping("/listarMaterias")
    public List<DtoMateriaRespuesta> listarMaterias() {
        return materiaServicio.listarMaterias();
    }

    @GetMapping("/obtenerMateria")
    public DtoMateriaRespuesta obtenerMateria(@RequestParam Long id) {
        return materiaServicio.obtenerMateria(id);
    }

    @PutMapping("/actualizarMateria")
    public DtoMateriaRespuesta actualizarMateria(
            @RequestParam Long id,
            @RequestBody DtoMateriaConsulta dto) {
        return materiaServicio.actualizarMateria(id, dto);
    }

    @DeleteMapping("/eliminarMateria")
    public String eliminarMateria(@RequestParam Long id) {
        materiaServicio.eliminarMateria(id);
        return "Materia eliminada correctamente";
    }

    @GetMapping("/consultarCupos")
    public DtoMateriaRespuesta consultarCupos(@RequestParam Long id) {
        return materiaServicio.obtenerMateria(id);
    }

    @GetMapping("/obtenerCuposDisponible")
    public DtoMateriaCuposDisponibles obtenerCuposDisponibles(@RequestParam Long id) {
        return materiaServicio.obtenerCuposDisponibles(id);
    }

    @PutMapping("/descontarCupo")
    public DtoMateriaCuposDisponibles descontarCupo(@RequestParam Long id) {
        return materiaServicio.descontarCupo(id);
    }
}