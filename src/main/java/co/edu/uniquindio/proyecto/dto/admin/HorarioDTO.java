package co.edu.uniquindio.proyecto.dto.admin;

import java.sql.Time;
import java.time.LocalDateTime;

public record HorarioDTO(
        LocalDateTime dia,
        Time horaInicio,
        Time horaSalida
) {
}
