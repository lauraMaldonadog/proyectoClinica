package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Ciudad;
import co.edu.uniquindio.proyecto.modelo.EPS;
import co.edu.uniquindio.proyecto.modelo.TipoSangre;

import java.time.LocalDate;

public record PacienteDTO(
        LocalDate fechaNacimiento,

        String alergias,

        EPS codigoEps,

        TipoSangre codigoTipoSangre,

        Ciudad codigoCiudad
) {
}
