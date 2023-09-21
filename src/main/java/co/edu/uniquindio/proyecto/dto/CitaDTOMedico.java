package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDateTime;

public record CitaDTOMedico(
        int codigoCita,
        String nombrePaciente,
        LocalDateTime fecha,
        String motivo
) {
}
