package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.modelo.Cuenta;

public interface AutenticacionServicios {
    TokenDTO login(LoginDTO loginDTO) throws Exception;
}
