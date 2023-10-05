package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.enumeraciones.Especialidad;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoPQRS;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record DetallePQRSDTO(

        int codigo,
        EstadoPQRS estadoPQRS,
        String motivoPQRS,
        String nombrePaciente,
        String nombreMedico,
        Especialidad especialidad,
        LocalDateTime fecha,
        List<RespuestaDTO> mensajes

) {
}
