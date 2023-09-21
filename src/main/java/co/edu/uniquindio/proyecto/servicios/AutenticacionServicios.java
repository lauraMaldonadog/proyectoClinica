package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.modelo.Cuenta;

public interface AutenticacionServicios {
    String login(LoginDTO loginDTO);
}
