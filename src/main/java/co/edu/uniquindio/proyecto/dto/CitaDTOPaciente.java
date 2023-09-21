package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDateTime;

public record CitaDTOPaciente(
        int codigoCita,

       /* Pendiente
        String nombrePaciente,*/
        String nombreMedico,
        LocalDateTime fecha,
        String motivo

) {
}
