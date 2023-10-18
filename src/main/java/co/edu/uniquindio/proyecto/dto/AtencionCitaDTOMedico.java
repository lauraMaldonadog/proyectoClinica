package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Cita;

public record AtencionCitaDTOMedico(
        int codigoCita,

        String diagnotisco,


        String tratamiento,


        String notasMedicas) {
}
