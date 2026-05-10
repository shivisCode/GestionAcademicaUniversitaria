package edu.uce.ec.materia.repositorio;

import edu.uce.ec.materia.entidad.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepositorio extends JpaRepository<Materia, Long>{
    
}
