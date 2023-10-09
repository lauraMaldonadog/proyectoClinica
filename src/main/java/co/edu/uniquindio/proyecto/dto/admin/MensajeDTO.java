package co.edu.uniquindio.proyecto.dto.admin;

import java.time.LocalDate;

public record MensajeDTO(
        int id,
        String mensaje,
        LocalDate fecha
) {

}
