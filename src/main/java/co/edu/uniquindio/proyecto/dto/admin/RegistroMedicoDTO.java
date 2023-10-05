package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;
import co.edu.uniquindio.proyecto.enumeraciones.Especialidad;

import java.util.List;

public record RegistroMedicoDTO(

        String nombre,
        String cedula,
        Ciudad ciudad,
        Especialidad especialidad,
        String telefono,
        String correo,
        String password,
        String urlFoto,
        List<HorarioDTO> horarios
) {
}
