package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Cita;

public record AtencionCitaDTOMedico(
        int codigo,

        String diagnotisco,


        String tratamiento,


        String notasMedicas,

        Cita cita) {
}
