package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.modelo.Ciudad;
import co.edu.uniquindio.proyecto.modelo.Especialidad;
import co.edu.uniquindio.proyecto.modelo.Medico;
import co.edu.uniquindio.proyecto.repositorios.MedicoRepository;
import co.edu.uniquindio.proyecto.servicios.AdministradorServicios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AdministradorServiciosImpl implements AdministradorServicios {

    private final MedicoRepository medicoRepository;
    @Override
    public int crearMedico(MedicoDTOAdmin medico) throws Exception {

        Medico  medicoNuevo = new Medico();

        medicoNuevo.setNombre(medico.nombre());
        medicoNuevo.setCedula(medico.cedula());
        medicoNuevo.setCodigoCiudad(Ciudad.values()[medico.codigo()]);
        medicoNuevo.setTelefono(medico.telefono());
        medicoNuevo.setUrlFoto(medico.urlFoto());
        medicoNuevo.setCodigoEspecialidad(Especialidad.values()[medico.codigo()]);
        medicoNuevo.setCorreo(medico.correo());
        medicoNuevo.setPassword(medico.password());
        Medico medicoRegistrado = medicoRepository.save(medicoNuevo);
        return medicoRegistrado.getCodigo();
    }

    @Override
    public String actualizarMedico(int codigo, MedicoDTOAdmin medico) throws Exception {
        return null;
    }

    @Override
    public String eliminarMedico(int codigo) throws Exception {
        return null;
    }

    @Override
    public List<MedicoDTOAdmin> listarMedicos() throws Exception {
        return null;
    }

    @Override
    public InfoMedicoDTO obtenerMedico(int codigo) throws Exception {
        return null;
    }

    @Override
    public List<PQRSDTOAdmin> listarPQRS() throws Exception {
        return null;
    }

    @Override
    public String responderPQRS(int codigo) throws Exception {
        return null;
    }

    @Override
    public InfoPQRSDTO verDetallePQRS(int codigo) throws Exception {
        return null;
    }

    @Override
    public List<CitaDTOAdmin> listarCitas() throws Exception {
        return null;
    }
}
