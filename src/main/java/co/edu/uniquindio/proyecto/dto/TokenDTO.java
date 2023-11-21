package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO(

        java.util.Map<String, Object> mapWithClaims, @NotBlank
        String token



) {
}
