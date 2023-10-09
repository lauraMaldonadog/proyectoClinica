package co.edu.uniquindio.proyecto.dto;

<<<<<<< HEAD
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegistroMedicoDTO() {

    @NotNull @Min(0) @Max(3)
    String cedula;
    int codigoCiudad;
=======
import co.edu.uniquindio.proyecto.dto.admin.HorarioDTO;
import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;
import co.edu.uniquindio.proyecto.enumeraciones.Especialidad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroMedicoDTO(
        @NotNull @Length(max = 200)
        String nombre,

        @NotNull @Length(max = 10)
        String cedula,
        @NotNull @Min(0) @Max(3)
        Ciudad ciudad,
        @NotNull @Min(0) @Max(5)
        Especialidad especialidad,
        @NotNull @Length(max = 20)
        String telefono,
        @NotNull @Length(max = 20) @Email
        String correo,
        @NotNull                                // no tendran restricciones de  longuitud
        String password,
        @NotNull                                // no tendran restricciones de  longuitud
        String urlFoto,
        List<HorarioDTO> horarios
) {
>>>>>>> 529eb9375dcabfb203c2115b24f3b07a1022fb4c
}
