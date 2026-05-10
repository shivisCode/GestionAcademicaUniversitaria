package edu.uce.ec.estudiante.servicio;

import org.springframework.stereotype.Service;

import edu.uce.ec.estudiante.dto.DtoEstudianteConsulta;
import edu.uce.ec.estudiante.dto.DtoEstudianteRespuesta;
import edu.uce.ec.estudiante.entidad.Estudiante;
import edu.uce.ec.estudiante.repositorio.EstudianteRepositorio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteServicio {

    private final EstudianteRepositorio estudianteRepositorio;

    public EstudianteServicio(EstudianteRepositorio estudianteRepositorio) {
        this.estudianteRepositorio = estudianteRepositorio;
    }

    // Registrar estudiante
    public DtoEstudianteRespuesta guardarEstudiante(DtoEstudianteConsulta dto) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.getNombre());
        estudiante.setCedula(dto.getCedula());
        estudiante.setCorreo(dto.getCorreo());
        estudiante.setCarrera(dto.getCarrera());
        estudiante.setFechaRegistro(LocalDateTime.now());
        estudiante.setEstado(dto.getEstado());

        Estudiante estudianteGuardado = estudianteRepositorio.save(estudiante);

        return convertirARespuesta(estudianteGuardado);
    }

    // Listar estudiantes
    public List<DtoEstudianteRespuesta> listarEstudiantes() {
        return estudianteRepositorio.findAll()
                .stream()
                .map(this::convertirARespuesta)
                .collect(Collectors.toList());
    }

    // Buscar estudiante por ID
    public DtoEstudianteRespuesta obtenerEstudiante(Long id) {
        Estudiante estudiante = estudianteRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        return convertirARespuesta(estudiante);
    }

    // Actualizar estudiante
    public DtoEstudianteRespuesta actualizarEstudiante(Long id, DtoEstudianteConsulta dto) {
        Estudiante estudiante = estudianteRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        estudiante.setNombre(dto.getNombre());
        estudiante.setCedula(dto.getCedula());
        estudiante.setCorreo(dto.getCorreo());
        estudiante.setCarrera(dto.getCarrera());
        estudiante.setEstado(dto.getEstado());

        Estudiante estudianteActualizado = estudianteRepositorio.save(estudiante);

        return convertirARespuesta(estudianteActualizado);
    }

    // Eliminar estudiante
    public void eliminarEstudiante(Long id) {
        estudianteRepositorio.deleteById(id);
    }

    private DtoEstudianteRespuesta convertirARespuesta(Estudiante estudiante) {
        DtoEstudianteRespuesta respuesta = new DtoEstudianteRespuesta();
        respuesta.setId(estudiante.getId());
        respuesta.setNombre(estudiante.getNombre());
        respuesta.setCedula(estudiante.getCedula());
        respuesta.setCarrera(estudiante.getCarrera());
        respuesta.setEstado(estudiante.getEstado());
        respuesta.setCorreo(estudiante.getCorreo());
        return respuesta;
    }
}