package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.enumeraciones.EstadoPQRS;

import java.time.LocalDateTime;

public record ItemPQRSDTO(
        int codigo,
        EstadoPQRS estadoPQRS,
        String motivo,
        LocalDateTime fecha,
        String nombrePaciente
) {
}
