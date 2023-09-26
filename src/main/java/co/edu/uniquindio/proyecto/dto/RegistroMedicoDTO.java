package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroMedicoDTO(
        @NotNull
        @Length(max = 200)
        String nombre,

        @NotNull @Length(max = 10)
        String cedula,
        @NotNull @Min(0) @Max(3)
        int codigoCiudad,
        @NotNull @Min(0) @Max(5)
        int codigoEspecialidad,
        @NotNull @Length(max = 20)
        String telefono,
        @NotNull @Length(max = 20) @Email
        String correo,
        @NotNull
        String password,
        @NotNull
        String urlFoto,
        List< HorarioDTO > horarios
) {
}
