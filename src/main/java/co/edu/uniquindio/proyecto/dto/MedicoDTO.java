package co.edu.uniquindio.proyecto.dto;

import java.util.List;

public record MedicoDTO(
        String nombre,
        int codigo,
        String cedula,
        int codigoCiudad,
        int codigoEspecialidad,
        String telefono,
        String correo,
        String password,
        List< HorarioDTO > horarios
) {
}
