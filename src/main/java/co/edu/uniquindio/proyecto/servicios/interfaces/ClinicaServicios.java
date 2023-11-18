package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;
import co.edu.uniquindio.proyecto.enumeraciones.EPS;
import co.edu.uniquindio.proyecto.enumeraciones.Especialidad;
import co.edu.uniquindio.proyecto.enumeraciones.TipoSangre;

import java.util.List;

public interface ClinicaServicios {

    List<Ciudad> listarCiudades();
    List<Especialidad> listarEspecialidades();
    List<TipoSangre> listarTiposSangre();
    List<EPS> listarEps();

    //List<ItemMedicoPacienteDTO> listarMedicoPorEspecialidad(Especialidad especialidad) throws Exception;

}