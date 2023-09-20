package co.edu.uniquindio.proyecto.modelo;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Getter

public class Horario implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    private Time horaInicio;
    private Time horaFin;

    @ManyToOne
    private Medico codigoMedico;
    private LocalDateTime dia;
}


