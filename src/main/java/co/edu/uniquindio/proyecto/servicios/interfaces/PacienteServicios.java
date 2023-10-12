package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.CitaDTOPaciente;
import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.InfoPQRSDTO;
import co.edu.uniquindio.proyecto.dto.PacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPacienteDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface PacienteServicios {

    int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception;

    int editarPerfil(DetallePacienteDTO pacienteDTO) throws Exception;

    String eliminarCuenta(DetallePacienteDTO pacienteDTO) throws Exception;

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
