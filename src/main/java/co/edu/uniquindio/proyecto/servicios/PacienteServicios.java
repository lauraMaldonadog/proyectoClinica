package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.CitaDTOPaciente;
import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.InfoPQRSDTO;
import co.edu.uniquindio.proyecto.dto.PacienteDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface PacienteServicios {
    String registrarse(PacienteDTO paciente) throws Exception;

    String editarPerfil(PacienteDTO paciente) throws Exception;

    String eliminarCuenta(PacienteDTO paciente) throws Exception;

    EmailDTO enviarLinkRecuperacion(EmailDTO emailEnviar) throws Exception;

    String cambiarPassword(String contraseñaActual, String nuevaContrasenia) throws Exception;

    String agendarCita(CitaDTOPaciente cita) throws Exception;

    String crearPQRS(InfoPQRSDTO pqrsdto);

    List<InfoPQRSDTO> listarPQRSPaciente() throws Exception;

    String responderPQRS(int codigoPQRS, String mensaje); //Pendiente

    List<CitaDTOPaciente> listarCitasPaciente() throws Exception;

    List<CitaDTOPaciente> filtrarCitasPorFecha(LocalDateTime fechaFiltrar) throws Exception;

    List<CitaDTOPaciente> filtrarCitasPorMedico(String nombreMedicoFiltrar) throws Exception;

    CitaDTOPaciente verDetalleCita(int codigoCita) throws Exception;

}
