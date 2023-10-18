package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {


    List<Cita> findByFechaCita(LocalDateTime fecha);

    List<Cita> findByMedicoNombre(String nombreMedico);

    Cita findByCodigo(int codigo);



}
