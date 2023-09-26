package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.Medico;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository <Medico, Integer> {

    @Query("select m from Medico m where m.cedula = :cedula") // busca en la base de dato la cedula que entra en ":cedula" y retorna el numero de la cedula
    Medico buscaPorCedula(String cedula);                     // m es el alias de medico par amayor busqueda

    @Query("select  m from Medico m where  m.correo = :correo") // busca el correo electronico en los :correo
    void buscarPorCorreo(String correo);
}
