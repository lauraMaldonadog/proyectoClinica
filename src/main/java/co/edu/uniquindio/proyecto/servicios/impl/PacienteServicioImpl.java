package co.edu.uniquindio.proyecto.servicios.impl;



import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoCita;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoUsuario;
import co.edu.uniquindio.proyecto.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.*;
import co.edu.uniquindio.proyecto.repositorios.*;
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
    private final PQRSRepository pqrsRepo;
    private final MensajeRepository mensajeRepo;
    private final CuentaRepository cuentaRepo;


    @Override
    public int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception {
        if (cedulaRepetida(pacienteDTO.cedula())) {
            throw new Exception("La cedula " + pacienteDTO.cedula() + " ya está en uso");
        }
        if (correoRepetido(pacienteDTO.correo())) {
            throw new Exception("El correo " + pacienteDTO.correo() + " ya está en uso.");
        }
        Paciente paciente = new Paciente();
//Datos de la Cuenta
        paciente.setCorreo( pacienteDTO.correo() );
        paciente.setPassword( pacienteDTO.password() );
//Datos del Usuario
        paciente.setNombre( pacienteDTO.nombre() );
        paciente.setCedula( pacienteDTO.cedula() );
        paciente.setTelefono( pacienteDTO.telefono() );
        paciente.setCiudad( pacienteDTO.ciudad() );
        paciente.setUrlFoto( pacienteDTO.urlFoto() );
//Datos del Paciente
        paciente.setFechaNacimiento( pacienteDTO.fechaNacimiento() );
        paciente.setCodigoEps( pacienteDTO.eps() );
        paciente.setAlergias( pacienteDTO.alergias() );
        paciente.setCodigoTipoSangre( pacienteDTO.tipoSangre() );
        Paciente pacienteCreado = pacienteRepo.save( paciente );
        return pacienteCreado.getCodigo();
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
    public void eliminarCuenta(int codigo) throws Exception {

        Optional<Paciente> pacienteBuscado = pacienteRepo.findById(codigo);
        if (pacienteBuscado.isEmpty()) {
            throw new Exception("No existe un paciente con el código " + codigo);
        }
        pacienteRepo.delete(pacienteBuscado.get());
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
            throw new ResourceNotFoundException("El médico con el código " + citaDTOPaciente.codigoMedico() + " no existe");
        }

        // Validar que el paciente no tenga más de 3 citas agendadas
        List<Cita> citasDelPaciente = citaRepo.findByPaciente(optionalPaciente.get());
        if (citasDelPaciente.size() >= 3) {
            throw new Exception("El paciente ya tiene 3 citas agendadas. No se puede agendar más citas.");
        }

        // Validar que la cita no esté agendada sobre otra cita existente
        LocalDateTime fechaCita = citaDTOPaciente.fecha();
        List<Cita> citasDelMedico = citaRepo.findByMedicoAndFechaCita(optionalMedico.get(), fechaCita);
        if (!citasDelMedico.isEmpty()) {
            throw new Exception("El médico ya tiene una cita agendada para la misma fecha y hora.");
        }

        Cita cita = new Cita();
        cita.setPaciente(optionalPaciente.get());
        cita.setMedico(optionalMedico.get());
        cita.setEstadoCita(EstadoCita.EN_PROCESO);
        cita.setFechaCreacion(LocalDateTime.now());
        cita.setFechaCita(citaDTOPaciente.fecha());
        cita.setMotivo(citaDTOPaciente.motivo());
        // Envía el email aquí

        return citaRepo.save(cita).getCodigo();
    }

    @Override
    public String crearPQRS(DetallePQRSDTO pqrsdto) {
        // Crear una instancia de la entidad PQRS
        PQRS pqrs = new PQRS();

        // Establecer los valores de la PQRS a partir de los datos en el DTO
        pqrs.setFechaCreacion(LocalDateTime.now()); // Fecha de creación actual
        pqrs.setTipo("Tipo de PQRS"); // Establece el tipo de PQRS según tus requisitos
        pqrs.setEstado(pqrsdto.estadoPQRS());
        pqrs.setMotivo(pqrsdto.motivoPQRS());

        // Asignar el paciente (si es necesario) y otras propiedades

        // Guardar la PQRS en el repositorio
        pqrsRepo.save(pqrs);

        return "PQRS creada exitosamente";
    }


    @Override
    public List<PQRS> listarPQRSPaciente() throws Exception {
        return pqrsRepo.findAll();
    }
    @Override
    public int responderPQRS(RegistroRespuestaDTO respuestaPQRSPDTO) throws Exception {
        Optional<PQRS> opcionalPQRS = pqrsRepo.findById(respuestaPQRSPDTO.codigoPQRS());

        if (opcionalPQRS.isEmpty()) {
            throw new Exception("No existe un PQRS con el código: " + respuestaPQRSPDTO.codigoPQRS());
        }

        Optional<Cuenta> optionalCuenta = cuentaRepo.findById(respuestaPQRSPDTO.codigoPQRS());

        if (optionalCuenta.isEmpty()) {
            throw new Exception("No existe la cuenta con el código: " + respuestaPQRSPDTO.codigoPQRS());
        }

        Mensaje mensajeNuevo = new Mensaje();
        mensajeNuevo.setCodigoPQRS(opcionalPQRS.get());
        mensajeNuevo.setFechaCreacion(LocalDateTime.now());
        mensajeNuevo.setCodigoCuenta(optionalCuenta.get());
        mensajeNuevo.setMensaje(respuestaPQRSPDTO.mensaje());

        Mensaje respuesta = mensajeRepo.save(mensajeNuevo);

        return respuesta.getCodigo();
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

        List<Cita> listaCitas = citaRepo.findByFechaCita(fechaFiltrar);

        List<CitaDTOPaciente> respuesta = new ArrayList<>();

        if (listaCitas.isEmpty()){

            throw new Exception("No hay citas para esa fecha");
        }

        for (Cita c: listaCitas) {

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
    public List<CitaDTOPaciente> filtrarCitasPorMedico(String nombreMedicoFiltrar) throws Exception {
        // Buscar el médico por su nombre
        Medico medico = medicoRepo.findByNombre(nombreMedicoFiltrar);

        if (medico == null) {
            throw new ResourceNotFoundException("No se encontró ningún médico con el nombre: " + nombreMedicoFiltrar);
        }

        // Obtener todas las citas asociadas al médico
        List<Cita> citasDelMedico = citaRepo.findByMedico(medico);
        List<CitaDTOPaciente> respuesta = new ArrayList<>();

        if (citasDelMedico.isEmpty()) {
            throw new Exception("No existen citas para el médico: " + nombreMedicoFiltrar);
        }

        for (Cita cita : citasDelMedico) {
            respuesta.add(new CitaDTOPaciente(
                    cita.getCodigo(),
                    cita.getPaciente().getCodigo(),
                    cita.getFechaCita(),
                    cita.getMotivo()
            ));
        }

        return respuesta;
    }


    @Override
    public CitaDTOPaciente verDetalleCita(int codigoCita) throws Exception {

        /*Cita citaBuscada = citaRepo.findByCodigo(codigoCita);*/

        Optional<Cita> optional = citaRepo.findById(codigoCita);

        if (optional.isEmpty()) {
            throw new Exception("No hay citas que coincidan con el codigo ingresado");
        }

        Cita buscado = optional.get();



        return new CitaDTOPaciente(
                buscado.getCodigo(),
                buscado.getMedico().getCodigo(),
                buscado.getFechaCita(),
                buscado.getMotivo()
        );
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