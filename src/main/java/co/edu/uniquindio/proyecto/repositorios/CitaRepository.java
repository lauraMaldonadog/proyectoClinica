package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.Cita;
import co.edu.uniquindio.proyecto.modelo.Medico;
import co.edu.uniquindio.proyecto.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {


    List<Cita> findByPacienteCodigo(int codigo);

    List<Cita> findByPaciente(Paciente paciente);

    List<Cita> findByMedicoAndFechaCita(Medico medico, LocalDateTime fechaCita);

    List<Cita> findByFechaCita(LocalDateTime fechaFiltrar);

    List<Cita> findByMedico(Medico medico);

}
