package co.edu.uniquindio.proyecto.servicios.impl;


import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoCita;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoUsuario;
import co.edu.uniquindio.proyecto.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.AtencionCita;
import co.edu.uniquindio.proyecto.modelo.Cita;
import co.edu.uniquindio.proyecto.modelo.Medico;
import co.edu.uniquindio.proyecto.modelo.Paciente;
import co.edu.uniquindio.proyecto.repositorios.CitaRepository;
import co.edu.uniquindio.proyecto.repositorios.MedicoRepository;
import co.edu.uniquindio.proyecto.repositorios.PacienteRepository;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicios;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicios {

    private final PacienteRepository pacienteRepo;
    private final CitaRepository citaRepo;
    private final MedicoRepository medicoRepo;


    @Override
    public int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception {
        if (cedulaRepetida(pacienteDTO.cedula())) {
            throw new Exception("La cedula " + pacienteDTO.cedula() + " ya está en uso");
        }
        if (correoRepetido(pacienteDTO.correo())) {
            throw new Exception("El correo " + pacienteDTO.correo() + " ya está en uso.");
        }

        Paciente paciente = new Paciente();

        // Llamar al método de apoyo para establecer los valores comunes del paciente
        establecerValoresComunesPaciente(paciente, pacienteDTO);

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
            throw new Exception("El paciente con el código " + pacienteDTO.codigo() + " no existe");
        }

        Paciente buscado = opcional.get();

        // Llamar al método de apoyo para establecer los valores comunes del paciente
        establecerValoresComunesPaciente(buscado, pacienteDTO);

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

        // Cambiar el estado del paciente a INACTIVO en lugar de eliminar físicamente
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
    public int agendarCita(CitaDTOPaciente citaDTOPaciente) throws Exception {
        Optional<Paciente> optionalPaciente = pacienteRepo.findById(citaDTOPaciente.codigoPaciente());
        if (optionalPaciente.isEmpty()) {
            throw new ResourceNotFoundException("El paciente con el código " + citaDTOPaciente.codigoPaciente() + " no existe");
        }

        Optional<Medico> optionalMedico = medicoRepo.findById(citaDTOPaciente.codigoMedico());
        if (optionalMedico.isEmpty()) {
            throw new ResourceNotFoundException("El paciente con el código " + citaDTOPaciente.codigoPaciente() + " no existe");
        }
        //validaciones, solo maxim 3 citas, que la cita no esté agendada sobre otra cita que ya exista

        Cita cita = new Cita();
        cita.setPaciente(optionalPaciente.get());
        cita.setMedico(optionalMedico.get());
        cita.setEstadoCita(EstadoCita.EN_PROCESO);
        cita.setFechaCreacion(LocalDateTime.now());
        cita.setFechaCita(citaDTOPaciente.fecha());
        cita.setMotivo(citaDTOPaciente.motivo());
        //enviar email

        return citaRepo.save(cita).getCodigo();
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
    public List<CitaDTOPaciente> listarCitasPaciente(int codigo) throws Exception {
        List<Cita> citas = citaRepo.findByPacienteCodigo(codigo);
        List<CitaDTOPaciente> respuesta = new ArrayList<>();

        if (citas.isEmpty()) {
            throw new Exception("No existen citas creadas");
        }

        for (Cita c : citas) {
            respuesta.add(new CitaDTOPaciente(
                    c.getCodigo(),
                    c.getMedico().getCodigo(),
                    c.getFechaCita(),
                    c.getMotivo()

            ));
        }

        return respuesta;
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
    @Override
    public DetallePacienteDTO verDetallePaciente(int codigo) {
        Optional<Paciente> optional = pacienteRepo.findById(codigo);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("El paciente con el código " + codigo + " no existe");
        }

        Paciente paciente = optional.get();
        //Hacemos un mapeo de un objeto de tipo Paciente a un objeto de tipo DetallePacienteDTO
        return new DetallePacienteDTO( paciente.getCodigo(), paciente.getCedula(),
                paciente.getNombre(), paciente.getTelefono(), paciente.getUrlFoto(), paciente.getCiudad(),
                paciente.getFechaNacimiento(), paciente.getAlergias(), paciente.getCodigoEps(),
                paciente.getCodigoTipoSangre(), paciente.getCorreo() );


    }

    @Override
    public List<ItemPacienteDTO> listarTodos(){
        List<Paciente> pacientes = pacienteRepo.findAll();
        List<ItemPacienteDTO> repuesta = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            repuesta.add( new ItemPacienteDTO( paciente.getCodigo(), paciente.getCedula(),
                    paciente.getNombre(), paciente.getCiudad() ) );
        }
        return repuesta;
    }

    private void establecerValoresComunesPaciente(Paciente paciente, RegistroPacienteDTO pacienteDTO) {
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
    }

    private void establecerValoresComunesPaciente(Paciente paciente, DetallePacienteDTO pacienteDTO) {
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
    }






}
