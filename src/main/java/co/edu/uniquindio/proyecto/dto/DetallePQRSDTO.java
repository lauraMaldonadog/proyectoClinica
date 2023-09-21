package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record DetallePQRSDTO(

        int codigo,
        String motivoPQRS,
        String nombrePaciente,
        String nombreMedico,
        String especialidad,
        LocalDateTime fecha,
        List<RespuestaDTO> mensajes

) {
}
