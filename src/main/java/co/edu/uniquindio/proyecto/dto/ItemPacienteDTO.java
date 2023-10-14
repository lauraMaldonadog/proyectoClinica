package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;

public record ItemPacienteDTO(

    int codigo,
    String nombre,
    String cedula,
    Ciudad ciudad)
{
}
