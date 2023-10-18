package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDateTime;

public record CitaDTOPaciente(
        int codigoPaciente,
        int codigoMedico,
        LocalDateTime fecha,
        String motivo

) {
}
