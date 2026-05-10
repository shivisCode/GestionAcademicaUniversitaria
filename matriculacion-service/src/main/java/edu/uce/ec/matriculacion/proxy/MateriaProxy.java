package edu.uce.ec.matriculacion.proxy;

import edu.uce.ec.matriculacion.dto.DtoMateriaCuposDisponibles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MateriaProxy {

    private final RestTemplate restTemplate;

    @Value("${services.materia.url}")
    private String materiaServiceUrl;

    public MateriaProxy() {
        this.restTemplate = new RestTemplate();
    }

    public DtoMateriaCuposDisponibles obtenerMateria(Long id) {

        String url =
                materiaServiceUrl +
                "/api/materias/obtenerCuposDisponible?id=" + id;

        return restTemplate.getForObject(
                url,
                DtoMateriaCuposDisponibles.class
        );
    }

    public DtoMateriaCuposDisponibles descontarCupo(Long id) {

        String url =
                materiaServiceUrl +
                "/api/materias/descontarCupo?id=" + id;

        return restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.PUT,
                null,
                DtoMateriaCuposDisponibles.class
        ).getBody();
    }
}