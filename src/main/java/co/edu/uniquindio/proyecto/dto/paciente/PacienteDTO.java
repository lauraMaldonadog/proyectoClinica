package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;
import co.edu.uniquindio.proyecto.enumeraciones.EPS;
import co.edu.uniquindio.proyecto.enumeraciones.TipoSangre;

import java.time.LocalDate;

public record PacienteDTO(
        LocalDate fechaNacimiento,

        String alergias,

        EPS codigoEps,

        TipoSangre codigoTipoSangre,

        Ciudad codigoCiudad
) {
}
