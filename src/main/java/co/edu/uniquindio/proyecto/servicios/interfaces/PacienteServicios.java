package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.proyecto.modelo.PQRS;

import java.time.LocalDateTime;
import java.util.List;

public interface PacienteServicios {

    int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception;

    int editarPerfil(DetallePacienteDTO pacienteDTO) throws Exception;

    void eliminarCuenta(int codigo) throws Exception;

    EmailDTO enviarLinkRecuperacion(EmailDTO emailEnviar) throws Exception;

    String cambiarPassword(String contraseñaActual, String nuevaContrasenia) throws Exception;

    int agendarCita(CitaDTOPaciente cita) throws Exception;

    String crearPQRS(DetallePQRSDTO pqrsdto) throws Exception;

    List<PQRS> listarPQRSPaciente() throws Exception;

    int responderPQRS(RegistroRespuestaDTO respuestaPQRSPDTO) throws Exception;

    List<CitaDTOPaciente> listarCitasPaciente(int codigo) throws Exception;

    List<CitaDTOPaciente> filtrarCitasPorFecha(LocalDateTime fechaFiltrar) throws Exception;

    List<CitaDTOPaciente> filtrarCitasPorMedico(String nombreMedicoFiltrar) throws Exception;

    CitaDTOPaciente verDetalleCita(int codigoCita) throws Exception;

    DetallePacienteDTO verDetallePaciente(int codigo) throws Exception;

    List<ItemPacienteDTO> listarTodos();
}
