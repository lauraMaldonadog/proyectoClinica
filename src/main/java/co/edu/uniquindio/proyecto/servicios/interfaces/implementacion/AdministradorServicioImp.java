package co.edu.uniquindio.proyecto.servicios.interfaces.implementacion;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;
import co.edu.uniquindio.proyecto.enumeraciones.Especialidad;
import co.edu.uniquindio.proyecto.modelo.Medico;
import co.edu.uniquindio.proyecto.repositorios.MedicoRepository;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AdministradorServicioImp implements AdministradorServicios {

//    @Autowired // FOrma adecuada de usar un componente, carga el objeto en memoria para hacer uso de el, ya esta instanciado de forma generica, pero no es la mas adecuada que es una fomra de hcaer un constructor o poner el @RequieredArgsConstructor
    private final MedicoRepository medicoRepository;


    @Override
    public int crearMedico(RegistroMedicoDTO medico) throws Exception {

        Medico medicoNuevo = new Medico();
        medicoNuevo.setNombre(medico.nombre());
        medicoNuevo.setCedula(medico.cedula());
        medicoNuevo.setCodigoCiudad(Ciudad.values()[medico.codigoCiudad()]);
        medicoNuevo.setTelefono(medico.telefono());
        medicoNuevo.setUrlFoto(medico.urlFoto());
        medicoNuevo.setCodigoEspecialidad(Especialidad.values()[medico.codigoEspecialidad()]);

        //Metodos que el medico teine por herencia
        medicoNuevo.setCorreo(medico.correo());
        medicoNuevo.setPassword(medico.password());

        // programar prgrama defencivo

        if (estaRepetidoCorreo(medico.correo())){
            throw new Exception("El correo esta repetido");
        }

        if(estaRepetidoCedula(medico.cedula())){
            throw new Exception("La cedula esta repetida");
        }

        Medico medicoRegistrado =medicoRepository.save(medicoNuevo);

        return medicoRegistrado.getCodigo();
    }

    private boolean estaRepetidoCedula(String cedula) {
        medicoRepository.buscaPorCedula(cedula);
        return false;
    }

    private boolean estaRepetidoCorreo(String correo) {
        medicoRepository.buscarPorCorreo(correo);
        return false;
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
