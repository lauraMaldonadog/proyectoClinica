package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;
import co.edu.uniquindio.proyecto.enumeraciones.EPS;
import co.edu.uniquindio.proyecto.enumeraciones.TipoSangre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record RegistroPacienteDTO(

        @NotBlank
        @Length(max = 10, message = "La cédula debe tener máximo 10 caracteres")
        String cedula,
        @NotBlank
        @Length(max = 200, message = "El nombre debe tener máximo 200 caracteres")
        String nombre,
        @NotBlank
        @Length(max = 20, message = "El teléfono debe tener máximo 20 caracteres")
        String telefono,
        @NotBlank
        String urlFoto,
        @NotNull
        Ciudad ciudad,
        @NotNull
        @Future(message = "Seleccione una fecha de nacimiento correcta")
        LocalDate fechaNacimiento,
        @NotBlank
        String alergias,
        @NotNull
        EPS eps,
        @NotNull
        TipoSangre tipoSangre,
        @NotBlank
        @Length(max = 80, message = "El correo debe tener máximo 80 caracteres")
        @Email(message = "Ingrese una dirección de correo electrónico válida")
        String correo,
        @NotBlank
        String password,
        @NotBlank
        String confirmaPassword
) {
}
