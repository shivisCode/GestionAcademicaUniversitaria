package edu.uce.ec.matriculacion.servicio;

import edu.uce.ec.matriculacion.dto.DtoComprobanteConsulta;
import edu.uce.ec.matriculacion.proxy.ComprobanteProxy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ComprobanteAsyncServicio {

    private final ComprobanteProxy comprobanteProxy;

    public ComprobanteAsyncServicio(ComprobanteProxy comprobanteProxy) {
        this.comprobanteProxy = comprobanteProxy;
    }

    @Async
    public void generarComprobanteAsync(DtoComprobanteConsulta dto) {
        try {
            comprobanteProxy.generarComprobante(dto);
        } catch (Exception e) {
            System.out.println("Error generando comprobante: " + e.getMessage());
        }
    }
}