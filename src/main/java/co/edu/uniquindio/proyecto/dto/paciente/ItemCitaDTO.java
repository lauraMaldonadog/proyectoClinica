package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.enumeraciones.EstadoCita;
import co.edu.uniquindio.proyecto.modelo.Medico;
import co.edu.uniquindio.proyecto.modelo.Paciente;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record ItemCitaDTO(

         int codigoCita,

        String nombrePaciente,


        LocalDateTime fechaCita,

        EstadoCita estadoCita


) {
}
