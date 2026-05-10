package edu.uce.ec.comprobante.servicio;

import edu.uce.ec.comprobante.dto.DtoComprobanteConsulta;
import edu.uce.ec.comprobante.dto.DtoComprobanteCorreo;
import edu.uce.ec.comprobante.dto.DtoComprobanteRespuesta;
import edu.uce.ec.comprobante.dto.DtoEstudianteRespuesta;
import edu.uce.ec.comprobante.email.EmailServicio;
import edu.uce.ec.comprobante.entidad.Comprobante;
import edu.uce.ec.comprobante.pdf.PdfGenerador;
import edu.uce.ec.comprobante.proxy.EstudianteProxy;
import edu.uce.ec.comprobante.repositorio.ComprobanteRepositorio;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ComprobanteServicio {

    private final ComprobanteRepositorio comprobanteRepositorio;
    private final PdfGenerador pdfGenerador;
    private final EmailServicio emailServicio;
    private final EstudianteProxy estudianteProxy;

    public ComprobanteServicio(
            ComprobanteRepositorio comprobanteRepositorio,
            PdfGenerador pdfGenerador,
            EmailServicio emailServicio,
            EstudianteProxy estudianteProxy) {

        this.comprobanteRepositorio = comprobanteRepositorio;
        this.pdfGenerador = pdfGenerador;
        this.emailServicio = emailServicio;
        this.estudianteProxy = estudianteProxy;
    }

    public DtoComprobanteRespuesta generarComprobante(
            DtoComprobanteConsulta dto) {

        Comprobante comprobante = new Comprobante();

        comprobante.setMatriculaId(dto.getMatriculaId());
        comprobante.setEstudianteId(dto.getEstudianteId());
        comprobante.setMateriaId(dto.getMateriaId());
        comprobante.setTipoComprobante(dto.getTipoComprobante());
        comprobante.setFechaEmision(LocalDateTime.now());
        comprobante.setEstado(true);

        String codigo =
                "COMP-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();

        comprobante.setCodigoComprobante(codigo);

        Comprobante comprobanteGuardado =
                comprobanteRepositorio.save(comprobante);

        DtoComprobanteCorreo comprobanteCorreo = new DtoComprobanteCorreo();
        comprobanteCorreo.setCodigoComprobante(codigo);
        comprobanteCorreo.setMatriculaId(comprobante.getMateriaId());
        comprobanteCorreo.setNombreEstudiante(dto.getNombreEstudiante());
        comprobanteCorreo.setEstado(comprobante.getEstado());
        comprobanteCorreo.setFechaEmision(comprobante.getFechaEmision());
        comprobanteCorreo.setMateria(dto.getMateria());
        comprobanteCorreo.setTipoComprobante("MATRICULA");
        // Generar PDF
        String rutaPdf =
                pdfGenerador.generarPdf(comprobanteCorreo);
        
        DtoEstudianteRespuesta estudiante = estudianteProxy.obtenerEstudiante(dto.getEstudianteId());
        System.out.println(estudiante.getCorreo());
        // Enviar correo
        emailServicio.enviarComprobante(
                estudiante.getCorreo(),
                "Comprobante de Matrícula",
                "Adjunto encontrará su comprobante de matrícula.",
                rutaPdf
        );

        return convertirARespuesta(comprobanteGuardado);
    }

    public List<DtoComprobanteRespuesta> listarComprobantes() {

        return comprobanteRepositorio.findAll()
                .stream()
                .map(this::convertirARespuesta)
                .collect(Collectors.toList());
    }

    public DtoComprobanteRespuesta obtenerComprobante(Long id) {

        Comprobante comprobante =
                comprobanteRepositorio.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Comprobante no encontrado"
                                ));

        return convertirARespuesta(comprobante);
    }

    public DtoComprobanteRespuesta anularComprobante(Long id) {

        Comprobante comprobante =
                comprobanteRepositorio.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Comprobante no encontrado"
                                ));

        comprobante.setEstado(false);

        Comprobante comprobanteActualizado =
                comprobanteRepositorio.save(comprobante);

        return convertirARespuesta(comprobanteActualizado);
    }

    private DtoComprobanteRespuesta convertirARespuesta(
            Comprobante comprobante) {

        DtoComprobanteRespuesta respuesta =
                new DtoComprobanteRespuesta();

        respuesta.setId(comprobante.getId());
        respuesta.setMatriculaId(comprobante.getMatriculaId());
        respuesta.setEstudianteId(comprobante.getEstudianteId());
        respuesta.setMateriaId(comprobante.getMateriaId());
        respuesta.setCodigoComprobante(
                comprobante.getCodigoComprobante()
        );
        respuesta.setTipoComprobante(
                comprobante.getTipoComprobante()
        );
        respuesta.setFechaEmision(
                comprobante.getFechaEmision()
        );
        respuesta.setEstado(comprobante.getEstado());

        return respuesta;
    }
}