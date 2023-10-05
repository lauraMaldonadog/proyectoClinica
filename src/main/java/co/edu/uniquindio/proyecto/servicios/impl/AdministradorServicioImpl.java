package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.HorarioDTO;
import co.edu.uniquindio.proyecto.dto.admin.ItemCitaAdminDTO;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoPQRS;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoUsuario;
import co.edu.uniquindio.proyecto.modelo.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdministradorServicios {

    private final MedicoRepository medicoRepo;
    private final PQRSRepository pqrsRepo;
    private final CuentaRepository cuentaRepo;
    private final MensajeRepository mensajeRepo;
    private final CitaRepository citaRepo;
    private final HorarioRepository horarioRepo;

    @Override
    public int crearMedico(RegistroMedicoDTO medicoDTO) throws Exception {
        if (cedulaRepetida(medicoDTO.cedula())) {
            throw new Exception("La cedula " + medicoDTO.cedula() + " ya esta en uso");
        }
        if (correoRepetido(medicoDTO.correo())) {
            throw new Exception("El correo " + medicoDTO.correo() + " ya esta en uso.");
        }

        Medico medico = new Medico();
        medico.setCedula(medicoDTO.cedula());
        medico.setTelefono(medicoDTO.telefono());
        medico.setNombre(medicoDTO.nombre());
        medico.setEspecialidad(medicoDTO.especialidad());
        medico.setCiudad(medicoDTO.ciudad());
        medico.setCorreo(medicoDTO.correo());
        medico.setPassword(medicoDTO.password());
        medico.setUrlFoto(medico.getUrlFoto());
        medico.setEstadoUsuario(EstadoUsuario.ACTIVO);

        Medico nuevoMedico = medicoRepo.save(medico);
        asignarHorarioMedico(nuevoMedico, medicoDTO.horarios());

        return medico.getCodigo();
    }

    private boolean correoRepetido(String correo) {
        return medicoRepo.findByCorreo(correo) != null;
    }

    private boolean cedulaRepetida(String cedula) {
        return medicoRepo.findByCedula(cedula) != null;
    }

    private void asignarHorarioMedico(Medico nuevoMedico, List<HorarioDTO> horarios) {
        for (HorarioDTO h : horarios) {

            Horario hm = new Horario();
            hm.setDia(h.dia());
            hm.setHoraInicio(h.horaInicio());
            hm.setHoraFin(h.horaSalida());
            hm.setCodigoMedico(nuevoMedico);

            horarioRepo.save(hm);
        }
    }

    private int actualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception {

        Optional<Medico> opcional = medicoRepo.findById(medicoDTO.codigo());
        if (opcional.isEmpty()) {
            throw new Exception("El medico con el codigo " + medicoDTO.codigo() + " no existe");
        }

        Medico buscado = opcional.get();
        buscado.setCedula(medicoDTO.cedula());
        buscado.setTelefono(medicoDTO.telefono());
        buscado.setNombre(medicoDTO.nombre());
        buscado.setEspecialidad(medicoDTO.especialidad());
        buscado.setCiudad(medicoDTO.ciudad());
        buscado.setCorreo(medicoDTO.correo());
        buscado.setUrlFoto(medicoDTO.urlFoto());

        medicoRepo.save(buscado);

        return buscado.getCodigo();
    }

    public void eliminarMedico(int codigo) throws Exception {
        Optional<Medico> optional = medicoRepo.findById(codigo);
        if (optional.isEmpty()) {
            throw new Exception("El medico con el codigo " + codigo + " no existe");
        }

        Medico buscado = optional.get();
        buscado.setEstadoUsuario(EstadoUsuario.INACTIVO);
        medicoRepo.save(buscado);
    }

    List<Medico> listaMedicos() throws Exception {
        List<Medico> medicos = medicoRepo.findAll();
        if (medicos.isEmpty()) {
            throw new Exception("No hay medicos registrados");
        }

        List<Medico> respuesta = new ArrayList<>();
        for (Medico m : medicos) {
            m.getCodigo();
            m.getNombre();
            m.getCedula();
            m.getEspecialidad();
            m.getUrlFoto();
        }
        return respuesta;
    }

    @Override
    public  InfoMedicoDTO  obenterMedico(int codigo) throws Exception {
        Optional<Medico> optional = medicoRepo.findById(codigo);

        if (optional.isEmpty()) {
            throw new Exception("NO hay medicos con el codigo" + codigo);
        }

        Medico buscado = optional.get();
        List<Horario> horarios = buscado.getListaHorario();
        List<HorarioDTO> horarioDTO = new ArrayList<>();

        for (Horario hm : horarios) {
            horarioDTO.add(new HorarioDTO(
                    hm.getDia(),
                    hm.getHoraInicio(),
                    hm.getHoraFin()
            ));
        }

        return new  InfoMedicoDTO (
                buscado.getCodigo(),
                buscado.getNombre(),
                buscado.getCedula(),
                buscado.getCiudad(),
                buscado.getEspecialidad(),
                buscado.getTelefono(),
                buscado.getCorreo(),
                buscado.getUrlFoto(),
                horarioDTO
        );
    }

    @Override
    public List<ItemPQRSDTO> listarPQRS() throws Exception {

        List<PQRS> listaPqrs = pqrsRepo.findAll();
        List<ItemPQRSDTO> respuesta = new ArrayList<>();

        for (PQRS p : listaPqrs) {

            respuesta.add(new ItemPQRSDTO(
                    p.getCodigo(),
                    p.getEstado(),
                    p.getMotivo(),
                    p.getFechaCreacion(),
                    p.getCita().getPaciente().getNombre()
            ));

        }

        return respuesta;
    }

    @Override
    public InfoPQRSDTO verDetallePQRS(int codigo) throws Exception {

        Optional<PQRS> opcional = pqrsRepo.findById(codigo);

        if (opcional.isEmpty()) {
            throw new Exception("No existe un PQRS con el código " + codigo);
        }

        PQRS buscado = opcional.get();

        return new InfoPQRSDTO(
                buscado.getCodigo(),
                buscado.getEstado(),
                buscado.getCita().getCodigo(),
                buscado.getMotivo(),
                buscado.getCita().getPaciente().getNombre(),
                buscado.getFechaCreacion(),
                new ArrayList<>()
        );
    }

    @Override
    public int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception {

        Optional<PQRS> opcionalPQRS = pqrsRepo.findById(registroRespuestaDTO.codigoPQRS());

        if (opcionalPQRS.isEmpty()) {
            throw new Exception("No existe un PQRS con el código " + registroRespuestaDTO.codigoPQRS());
        }

        Optional<Cuenta> opcionalCuenta = cuentaRepo.findById(registroRespuestaDTO.codigoCuenta());

        if (opcionalCuenta.isEmpty()) {
            throw new Exception("No existe una cuenta con el código " + registroRespuestaDTO.codigoCuenta());
        }

        Mensaje mensajeNuevo = new Mensaje();
        mensajeNuevo.setCodigoPQRS(opcionalPQRS.get());
        mensajeNuevo.setFechaCreacion(LocalDateTime.now());
        mensajeNuevo.setCodigoCuenta(opcionalCuenta.get());
        mensajeNuevo.setMensaje(registroRespuestaDTO.mensaje());

        Mensaje respuesta = mensajeRepo.save(mensajeNuevo);

        return respuesta.getCodigo();
    }

    @Override
    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) throws Exception {

        Optional<PQRS> opcional = pqrsRepo.findById(codigoPQRS);

        if (opcional.isEmpty()) {
            throw new Exception("No existe un PQRS con el código " + codigoPQRS);
        }

        PQRS pqrs = opcional.get();
        pqrs.setEstado(estadoPQRS);

        pqrsRepo.save(pqrs);
    }

    @Override
    public List<CitaDTOAdmin> listarCitas() throws Exception {

        List<Cita> citas = citaRepo.findAll();
        List<CitaDTOAdmin> respuesta = new ArrayList<>();

        if (citas.isEmpty()) {
            throw new Exception("No existen citas creadas");
        }

        for (Cita c : citas) {
            respuesta.add(new CitaDTOAdmin(
                    c.getCodigo(),
                    c.getPaciente().getCedula(),
                    c.getPaciente().getNombre(),
                    c.getMedico().getEspecialidad(),
                    c.getFechaCita(),
                    c.getMotivo()

            ));
        }

        return respuesta;
    }
}





