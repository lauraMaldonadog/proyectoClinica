package co.edu.uniquindio.proyecto.servicios.impl;


import co.edu.uniquindio.proyecto.dto.CitaDTOPaciente;
import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.InfoPQRSDTO;
import co.edu.uniquindio.proyecto.dto.PacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoUsuario;
import co.edu.uniquindio.proyecto.modelo.Medico;
import co.edu.uniquindio.proyecto.modelo.Paciente;
import co.edu.uniquindio.proyecto.repositorios.PacienteRepository;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicios;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PacienteServicioImpl implements PacienteServicios {

    private final PacienteRepository pacienteRepo;



    @Override
    public int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception {
        if (cedulaRepetida(pacienteDTO.cedula())) {
            throw new Exception("La cedula " + pacienteDTO.cedula() + " ya esta en uso");
        }
        if (correoRepetido(pacienteDTO.correo())) {
            throw new Exception("El correo " + pacienteDTO.correo() + " ya esta en uso.");
        }

        Paciente paciente = new Paciente();

        paciente.setCedula(pacienteDTO.cedula());
        paciente.setNombre(pacienteDTO.nombre());
        paciente.setTelefono(pacienteDTO.telefono());
        paciente.setUrlFoto(pacienteDTO.urlFoto());
        paciente.setCiudad(pacienteDTO.ciudad());
        paciente.setFechaNacimiento(pacienteDTO.fechaNacimiento());
        paciente.setAlergias(pacienteDTO.alergias());
        paciente.setCodigoEps(pacienteDTO.eps());
        paciente.setCodigoTipoSangre(pacienteDTO.tipoSangre());
        paciente.setCorreo(pacienteDTO.correo());
        paciente.setPassword(pacienteDTO.password());

        paciente.setEstadoUsuario(EstadoUsuario.ACTIVO);

        Paciente nuevoPaciente = pacienteRepo.save(paciente);


        return paciente.getCodigo();
    }

    private boolean correoRepetido(String correo) {
        return pacienteRepo.findByCorreo(correo) != null;
    }

    private boolean cedulaRepetida(String cedula) {
        return pacienteRepo.findByCedula(cedula) != null;
    }




    @Override
    public int editarPerfil(DetallePacienteDTO pacienteDTO) throws Exception {

        Optional<Paciente> opcional = pacienteRepo.findById(pacienteDTO.codigo());
        if (opcional.isEmpty()) {
            throw new Exception("El paciente con el codigo " + pacienteDTO.codigo() + " no existe");
        }

        Paciente buscado = opcional.get();
        buscado.setCedula(pacienteDTO.cedula());
        buscado.setNombre(pacienteDTO.nombre());
        buscado.setTelefono(pacienteDTO.telefono());
        buscado.setUrlFoto(pacienteDTO.urlFoto());
        buscado.setCiudad(pacienteDTO.ciudad());
        buscado.setFechaNacimiento(pacienteDTO.fechaNacimiento());
        buscado.setAlergias(pacienteDTO.alergias());
        buscado.setCodigoEps(pacienteDTO.eps());
        buscado.setCodigoTipoSangre(pacienteDTO.tipoSangre());

        pacienteRepo.save(buscado);

        return buscado.getCodigo();

    }

    @Override
    public String eliminarCuenta(DetallePacienteDTO pacienteDTO) throws Exception {

        String respuesta = "Su cuenta ha sido borrada satisfactoriamente";

        Optional<Paciente> optional = pacienteRepo.findById(pacienteDTO.codigo());
        if (optional.isEmpty()) {
            throw new Exception("El paciente con el codigo " + pacienteDTO.codigo() + " no existe");
        }

        Paciente buscado = optional.get();
        buscado.setEstadoUsuario(EstadoUsuario.INACTIVO);
        pacienteRepo.save(buscado);
        return respuesta;
    }

    @Override
    public EmailDTO enviarLinkRecuperacion(EmailDTO emailEnviar) throws Exception {
        return null;
    }

    @Override
    public String cambiarPassword(String contraseñaActual, String nuevaContrasenia) throws Exception {

       /* String respuesta = "Su contraseña ha sido cambiada";*/



        return null;
    }

    @Override
    public String agendarCita(CitaDTOPaciente cita) throws Exception {
        return null;
    }

    @Override
    public String crearPQRS(InfoPQRSDTO pqrsdto) {
        return null;
    }

    @Override
    public List<InfoPQRSDTO> listarPQRSPaciente() throws Exception {
        return null;
    }

    @Override
    public String responderPQRS(int codigoPQRS, String mensaje) {
        return null;
    }

    @Override
    public List<CitaDTOPaciente> listarCitasPaciente() throws Exception {
        return null;
    }

    @Override
    public List<CitaDTOPaciente> filtrarCitasPorFecha(LocalDateTime fechaFiltrar) throws Exception {
        return null;
    }

    @Override
    public List<CitaDTOPaciente> filtrarCitasPorMedico(String nombreMedicoFiltrar) throws Exception {
        return null;
    }

    @Override
    public CitaDTOPaciente verDetalleCita(int codigoCita) throws Exception {
        return null;
    }
}
