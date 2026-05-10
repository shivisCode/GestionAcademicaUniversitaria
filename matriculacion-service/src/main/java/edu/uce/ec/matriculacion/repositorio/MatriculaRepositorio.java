package edu.uce.ec.matriculacion.repositorio;

import edu.uce.ec.matriculacion.entidad.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepositorio extends JpaRepository<Matricula, Long> {

}