package edu.uce.ec.matriculacion.proxy;

import edu.uce.ec.matriculacion.dto.DtoComprobanteConsulta;
import edu.uce.ec.matriculacion.dto.DtoComprobanteRespuesta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ComprobanteProxy {

    private final RestTemplate restTemplate;

    @Value("${comprobante.service.url}")
    private String comprobanteServiceUrl;

    public ComprobanteProxy() {
        this.restTemplate = new RestTemplate();
    }

    public DtoComprobanteRespuesta generarComprobante(
            DtoComprobanteConsulta dto) {

        String url =
                comprobanteServiceUrl +
                "/api/comprobantes/generarComprobante";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DtoComprobanteConsulta> request =
                new HttpEntity<>(dto, headers);

        ResponseEntity<DtoComprobanteRespuesta> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        request,
                        DtoComprobanteRespuesta.class
                );

        return response.getBody();
    }
}