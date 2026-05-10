package edu.uce.ec.matriculacion.servicio;

import edu.uce.ec.matriculacion.dto.DtoComprobanteConsulta;
import edu.uce.ec.matriculacion.dto.DtoMateriaCuposDisponibles;
import edu.uce.ec.matriculacion.dto.DtoMatriculaConsulta;
import edu.uce.ec.matriculacion.dto.DtoMatriculaRespuesta;
import edu.uce.ec.matriculacion.entidad.Matricula;
import edu.uce.ec.matriculacion.proxy.MateriaProxy;
import edu.uce.ec.matriculacion.repositorio.MatriculaRepositorio;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculacionServicio {

    private final MatriculaRepositorio matriculaRepositorio;
    private final MateriaProxy materiaProxy;
    private final ComprobanteAsyncServicio comprobanteAsyncServicio;

    public MatriculacionServicio(MatriculaRepositorio matriculaRepositorio,
                                 MateriaProxy materiaProxy,ComprobanteAsyncServicio comprobanteAsyncServicio) {
        this.matriculaRepositorio = matriculaRepositorio;
        this.materiaProxy = materiaProxy;
        this.comprobanteAsyncServicio= comprobanteAsyncServicio;
    }

    public DtoMatriculaRespuesta registrarMatricula(DtoMatriculaConsulta dto) {
        DtoComprobanteConsulta dtoComprobante = new DtoComprobanteConsulta();
        DtoMateriaCuposDisponibles materia = materiaProxy.obtenerMateria(dto.getMateriaId());

        if (materia == null) {
            throw new RuntimeException("No se pudo consultar la materia");
        }

        if (materia.getCupoDisponible() == null || materia.getCupoDisponible() <= 0) {
            throw new RuntimeException("No existen cupos disponibles para esta materia");
        }

        Matricula matricula = new Matricula();
        matricula.setEstudianteId(dto.getEstudianteId());
        matricula.setMateriaId(dto.getMateriaId());
        matricula.setFechaMatricula(LocalDateTime.now());
        matricula.setEstado(true);

        Matricula matriculaGuardada = matriculaRepositorio.save(matricula);
        materiaProxy.descontarCupo(dto.getMateriaId());
        
        //Armamos la estructura del comprobante
        dtoComprobante.setMatriculaId(matriculaGuardada.getId());
        dtoComprobante.setEstudianteId(matriculaGuardada.getEstudianteId());
        dtoComprobante.setMateriaId(matriculaGuardada.getMateriaId());
        dtoComprobante.setTipoComprobante("MATRICULA");
        dtoComprobante.setMateria(materia.getNombre());
        dtoComprobante.setCodigoMateria(materia.getCodigo());
        comprobanteAsyncServicio.generarComprobanteAsync(dtoComprobante);
        return convertirARespuesta(matriculaGuardada);
    }

    public List<DtoMatriculaRespuesta> listarMatriculas() {
        return matriculaRepositorio.findAll()
                .stream()
                .map(this::convertirARespuesta)
                .collect(Collectors.toList());
    }

    public DtoMatriculaRespuesta obtenerMatricula(Long id) {
        Matricula matricula = matriculaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));

        return convertirARespuesta(matricula);
    }

    public DtoMatriculaRespuesta anularMatricula(Long id) {
        Matricula matricula = matriculaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));

        matricula.setEstado(false);

        Matricula matriculaActualizada = matriculaRepositorio.save(matricula);

        return convertirARespuesta(matriculaActualizada);
    }

    private DtoMatriculaRespuesta convertirARespuesta(Matricula matricula) {
        DtoMatriculaRespuesta respuesta = new DtoMatriculaRespuesta();

        respuesta.setId(matricula.getId());
        respuesta.setEstudianteId(matricula.getEstudianteId());
        respuesta.setMateriaId(matricula.getMateriaId());
        respuesta.setFechaMatricula(matricula.getFechaMatricula());
        respuesta.setEstado(matricula.getEstado());

        return respuesta;
    }
}