package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;
import co.edu.uniquindio.proyecto.enumeraciones.EPS;
import co.edu.uniquindio.proyecto.enumeraciones.TipoSangre;

import java.time.LocalDate;

public record RegistroPacienteDTO(

        String cedula,
        String nombre,
        String telefono,
        String urlFoto,
        Ciudad ciudad,
        LocalDate fechaNacimiento,
        String alergias,
        EPS eps,
        TipoSangre tipoSangre,
        String correo,
        String password

) {
}
