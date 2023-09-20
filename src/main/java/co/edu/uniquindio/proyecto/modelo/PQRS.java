package co.edu.uniquindio.proyecto.modelo;

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

    private String motivo;

    @ManyToOne
    private Cita codigoCita;

    private EstadoPQRS codigoEstado;
}

