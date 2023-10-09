package co.edu.uniquindio.proyecto.enumeraciones;

import lombok.Getter;

@Getter
public enum EPS {

    NUEVA_EPS ("Nueva Eps"),
    SURA ("Sura"),
    SANITAS("Sanitas");

    private String nombre;


    EPS(String nombre) {
        this.nombre = nombre;
    }
}
