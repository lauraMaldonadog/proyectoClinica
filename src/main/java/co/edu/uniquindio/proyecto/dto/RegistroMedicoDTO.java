package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegistroMedicoDTO() {

    @NotNull @Min(0) @Max(3)
    String cedula;
    int codigoCiudad;
}
