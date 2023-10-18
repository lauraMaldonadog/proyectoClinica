package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.PQRS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PQRSRepository extends JpaRepository <PQRS, Integer> {

    PQRS findByCodigo(int codigo);


}
