package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.enumeraciones.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepo extends JpaRepository<Sede, Integer> {

    Sede findByNombre(String nombre);

}
