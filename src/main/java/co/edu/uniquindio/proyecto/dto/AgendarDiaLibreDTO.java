package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDate;

public record AgendarDiaLibreDTO(
        int codigoMedico,
        LocalDate diaLibre
) {
}
