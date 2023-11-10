package co.edu.uniquindio.proyecto.enumeraciones;


public enum Sede {

    ARMENIA("Armenia"),

    PEREIRA("Pereira"),

    MANIZALES("Manizales"),

    IBAGUE("Ibagué");

    private String nombre;


    Sede(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

