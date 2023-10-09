package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.AtencionCitaDTOMedico;
import co.edu.uniquindio.proyecto.dto.CitaDTOMedico;

import java.util.List;

public interface MedicoServicios {
    List<CitaDTOMedico> citasPendientes() throws Exception;
    AtencionCitaDTOMedico atenderCitas(int codigoCita) throws Exception;
    List<CitaDTOMedico> listarCitasPacientes();
    String agendarDiaLibre(CitaDTOMedico cita) throws Exception;
    List<CitaDTOMedico> listarCitasRealizadasMedico();
}
