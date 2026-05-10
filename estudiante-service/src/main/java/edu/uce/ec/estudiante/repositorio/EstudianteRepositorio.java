package edu.uce.ec.estudiante.repositorio;

import edu.uce.ec.estudiante.entidad.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepositorio extends JpaRepository<Estudiante, Long> {
}
