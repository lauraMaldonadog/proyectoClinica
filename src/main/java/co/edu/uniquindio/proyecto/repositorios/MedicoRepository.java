package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer > {

}
