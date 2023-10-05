package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.enumeraciones.EstadoPQRS;

import java.time.LocalDateTime;
import java.util.List;

public record InfoPQRSDTO(
        int codigo,

        EstadoPQRS estado,
        int codigoCita,
        String motivo,
        String nombrePaciente,
        LocalDateTime fecha,
        List<String> mensajes


) {
}
