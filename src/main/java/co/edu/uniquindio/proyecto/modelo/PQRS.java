package co.edu.uniquindio.proyecto.modelo;

import co.edu.uniquindio.proyecto.enumeraciones.EstadoPQRS;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PQRS implements Serializable {

    @Id
    private int codigo;

    private LocalDateTime fechaCreacion;

    private String tipo;

    private EstadoPQRS estado;

    private String motivo;

    @ManyToOne
    private Cita cita;

    private EstadoPQRS codigoEstado;
}

