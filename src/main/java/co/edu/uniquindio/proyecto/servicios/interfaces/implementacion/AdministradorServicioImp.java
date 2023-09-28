package co.edu.uniquindio.proyecto.servicios.interfaces.implementacion;

import aj.org.objectweb.asm.Opcodes;
import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;
import co.edu.uniquindio.proyecto.enumeraciones.Especialidad;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoUsuario;
import co.edu.uniquindio.proyecto.modelo.Medico;
import co.edu.uniquindio.proyecto.repositorios.MedicoRepository;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorServicioImp implements AdministradorServicios {

//    @Autowired // FOrma adecuada de usar un componente, carga el objeto en memoria para hacer uso de el, ya esta instanciado de forma generica, pero no es la mas adecuada que es una fomra de hcaer un constructor o poner el @RequieredArgsConstructor
    private final MedicoRepository medicoRepository;


    @Override
    public int crearMedico(RegistroMedicoDTO medicoDTO) throws Exception {

        Medico medicoNuevo = new Medico();
        medicoNuevo.setNombre(medicoDTO.nombre());
        medicoNuevo.setCedula(medicoDTO.cedula());
        medicoNuevo.setCodigoCiudad(medicoDTO.ciudad());
        medicoNuevo.setTelefono(medicoDTO.telefono());
        medicoNuevo.setUrlFoto(medicoDTO.urlFoto());
        medicoNuevo.setCodigoEspecialidad(medicoDTO.especialidad());

        //Metodos que el medico teine por herencia
        medicoNuevo.setCorreo(medicoDTO.correo());
        medicoNuevo.setPassword(medicoDTO.password());

        // programar prgrama defencivo

        if (estaRepetidoCorreo(medicoDTO.correo())){
            throw new Exception("El correo esta repetido");
        }

        if(estaRepetidoCedula(medicoDTO.cedula())){
            throw new Exception("La cedula esta repetida");
        }

        Medico medicoRegistrado =medicoRepository.save(medicoNuevo);    //Guarda el medico creado
        return medicoRegistrado.getCodigo();
    }

    private boolean estaRepetidoCedula(String cedula) {
        medicoRepository.findByCedula(cedula);
        return false;
    }

    private boolean estaRepetidoCorreo(String correo) {
        medicoRepository.findByCorreo(correo);
        return false;
    }


    @Override
    public String actualizarMedico(MedicoDTO medicoDTO) throws Exception {

        Optional <Medico> optional = medicoRepository.findById(medicoDTO.codigo());

        return null;
    }

    @Override
    public String eliminarMedico(int codigo) throws Exception {
        Optional<Medico> opcional = medicoRepository.findById(codigo);                          //El findby ya tiene establecido el findBy de id el cual hace la comparacion
        if (opcional.isEmpty()){
            throw new Exception("No esxiste un medico con el codigo"+codigo);
        }

        Medico buscado = opcional.get();
        buscado.setEstado(EstadoUsuario.INACTIVO);
        medicoRepository.save(buscado);
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
