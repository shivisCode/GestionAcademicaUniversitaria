package edu.uce.ec.materia.servicio;

import org.springframework.stereotype.Service;

import edu.uce.ec.materia.dto.DtoMateriaConsulta;
import edu.uce.ec.materia.dto.DtoMateriaCuposDisponibles;
import edu.uce.ec.materia.dto.DtoMateriaRespuesta;
import edu.uce.ec.materia.entidad.Materia;
import edu.uce.ec.materia.repositorio.MateriaRepositorio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateriaServicio {

    private final MateriaRepositorio materiaRepositorio;

    public MateriaServicio(MateriaRepositorio materiaRepositorio) {
        this.materiaRepositorio = materiaRepositorio;
    }

    // Registrar materia
    public DtoMateriaRespuesta guardarMateria(DtoMateriaConsulta dto) {

        Materia materia = new Materia();
        materia.setNombre(dto.getNombre());
        materia.setCodigo(dto.getCodigo());
        materia.setCreditos(dto.getCreditos());
        materia.setEstado(dto.getEstado());
        materia.setFechaRegistro(LocalDateTime.now());
        materia.setCupoTotal(dto.getCupoTotal());
        materia.setCupoDisponible(dto.getCupoDisponible());

        Materia materiaGuardada = materiaRepositorio.save(materia);

        return convertirARespuesta(materiaGuardada);
    }

    // Listar materias
    public List<DtoMateriaRespuesta> listarMaterias() {
        return materiaRepositorio.findAll()
                .stream()
                .map(this::convertirARespuesta)
                .collect(Collectors.toList());
    }

    // Buscar materia por ID
    public DtoMateriaRespuesta obtenerMateria(Long id) {

        Materia materia = materiaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        return convertirARespuesta(materia);
    }

    // Actualizar materia
    public DtoMateriaRespuesta actualizarMateria(Long id, DtoMateriaConsulta dto) {

        Materia materia = materiaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        materia.setNombre(dto.getNombre());
        materia.setCodigo(dto.getCodigo());
        materia.setCreditos(dto.getCreditos());
        materia.setEstado(dto.getEstado());
        materia.setCupoTotal(dto.getCupoTotal());
        materia.setCupoDisponible(dto.getCupoDisponible());
        
        Materia materiaActualizada = materiaRepositorio.save(materia);

        return convertirARespuesta(materiaActualizada);
    }

    // Eliminar materia
    public void eliminarMateria(Long id) {
        materiaRepositorio.deleteById(id);
    }

    // Consultar Cupos Disponibles
    public DtoMateriaCuposDisponibles obtenerCuposDisponibles(Long id) {
        DtoMateriaCuposDisponibles respuesta = new DtoMateriaCuposDisponibles();

        Materia materia = materiaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        respuesta.setCodigo(materia.getCodigo());
        respuesta.setNombre(materia.getNombre());
        respuesta.setCupoDisponible(materia.getCupoDisponible());
        return respuesta;
    }

    // Descontamos los cupos en base de Datos
    public DtoMateriaCuposDisponibles descontarCupo(Long id) {
        DtoMateriaCuposDisponibles respuesta = new DtoMateriaCuposDisponibles();
        Materia materia = materiaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        if (materia.getCupoDisponible() == null || materia.getCupoDisponible() <= 0) {
            throw new RuntimeException("No existen cupos disponibles");
        }

        materia.setCupoDisponible(materia.getCupoDisponible() - 1);

        Materia materiaActualizada = materiaRepositorio.save(materia);
        respuesta.setCodigo(materiaActualizada.getCodigo());
        respuesta.setNombre(materiaActualizada.getNombre());
        respuesta.setCupoDisponible(materiaActualizada.getCupoDisponible());
        return respuesta;
    }

    private DtoMateriaRespuesta convertirARespuesta(Materia materia) {

        DtoMateriaRespuesta respuesta = new DtoMateriaRespuesta();

        respuesta.setId(materia.getId());
        respuesta.setNombre(materia.getNombre());
        respuesta.setCodigo(materia.getCodigo());
        respuesta.setCreditos(materia.getCreditos());
        respuesta.setEstado(materia.getEstado());
        respuesta.setCupoTotal(materia.getCupoTotal());
        respuesta.setCupoDisponible(materia.getCupoDisponible());

        return respuesta;
    }
}