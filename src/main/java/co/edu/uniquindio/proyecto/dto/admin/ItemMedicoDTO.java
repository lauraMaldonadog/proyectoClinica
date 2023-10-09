package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.enumeraciones.Especialidad;

public record ItemMedicoDTO(
        String cedula,
        String nombre,
        String urlFoto,
        Especialidad especialidad
) {
}
