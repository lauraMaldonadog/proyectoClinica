package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoPQRS;

import java.util.List;

public interface AdministradorServicios {
    int crearMedico(RegistroMedicoDTO medico) throws Exception;

    int actualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception;

    void eliminarMedico(int codigo) throws Exception;

    List<ItemMedicoDTO> listarMedicos() throws Exception;

    InfoMedicoDTO obtenerMedico(int codigo) throws Exception;

    List<ItemPQRSDTO> listarPQRS() throws Exception;

    int responderPQRS(RegistroRespuestaDTO codigo) throws Exception;

    InfoPQRSDTO verDetallePQRS(int codigo) throws Exception;

    List<CitaDTOAdmin> listarCitas() throws Exception;
    void cambiarEstadoPQRS(int codigo, EstadoPQRS estadoPQRS) throws Exception;
}
