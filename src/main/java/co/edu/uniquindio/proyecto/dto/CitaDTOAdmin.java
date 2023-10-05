package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.enumeraciones.Especialidad;
import co.edu.uniquindio.proyecto.modelo.Medico;

import java.time.LocalDateTime;

public record CitaDTOAdmin(
        int codigoCita,
        String nombrePaciente,
        String nombreMedico,
        Especialidad especialidad,
        LocalDateTime fecha,
        String motivo
) {
}
