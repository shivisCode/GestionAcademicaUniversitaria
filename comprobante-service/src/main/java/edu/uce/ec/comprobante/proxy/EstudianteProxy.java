package edu.uce.ec.comprobante.proxy;

import edu.uce.ec.comprobante.dto.DtoEstudianteRespuesta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EstudianteProxy {

    private final RestTemplate restTemplate;

    @Value("${services.estudiante.url}")
    private String estudianteServiceUrl;

    public EstudianteProxy() {
        this.restTemplate = new RestTemplate();
    }

    public DtoEstudianteRespuesta obtenerEstudiante(Long id) {

        String url =
                estudianteServiceUrl +
                "/api/estudiantes/obtenerEstudiante?id=" + id;

        return restTemplate.getForObject(
                url,
                DtoEstudianteRespuesta.class
        );
    }
}