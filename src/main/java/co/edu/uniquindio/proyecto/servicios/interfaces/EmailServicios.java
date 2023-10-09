package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.EmailDTO;

public interface EmailServicios {
    String enviarCorreo(EmailDTO emailDTO) throws Exception;
}
