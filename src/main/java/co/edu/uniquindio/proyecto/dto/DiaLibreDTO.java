package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record DiaLibreDTO(
        @NotNull
        int codigoMedico,
        @NotNull
        @Future(message = "La fecha debe ser futura")
        LocalDate dia
) {

}