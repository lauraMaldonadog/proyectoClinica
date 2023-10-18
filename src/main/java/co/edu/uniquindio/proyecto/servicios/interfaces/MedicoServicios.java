package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.AtencionCitaDTOMedico;
import co.edu.uniquindio.proyecto.dto.CitaDTOMedico;

import java.util.List;

public interface MedicoServicios {
    List<CitaDTOMedico> citasPendientes() throws Exception;
    void atenderCitas(AtencionCitaDTOMedico atencionCitaDTOMedico) throws Exception;
    List<CitaDTOMedico> listarCitasPacientes() throws Exception;
    String agendarDiaLibre(CitaDTOMedico cita) throws Exception;
    List<CitaDTOMedico> listarCitasRealizadasMedico() throws Exception;
}
