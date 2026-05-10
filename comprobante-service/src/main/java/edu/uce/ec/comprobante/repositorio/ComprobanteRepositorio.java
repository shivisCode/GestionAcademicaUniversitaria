package edu.uce.ec.comprobante.repositorio;

import edu.uce.ec.comprobante.entidad.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprobanteRepositorio extends JpaRepository<Comprobante, Long> {
}