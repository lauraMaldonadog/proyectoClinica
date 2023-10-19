package co.edu.uniquindio.proyecto.modelo;

import co.edu.uniquindio.proyecto.enumeraciones.EstadoCita;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yaml.snakeyaml.events.Event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Cita implements Serializable {

    @Id
    @Lob
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaCita;

    private String motivo;

    @ManyToOne
    @JoinColumn(nullable = false) // Asegúrate de usar el nombre correcto de la columna
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    private EstadoCita estadoCita;

    @OneToOne(mappedBy = "cita")
    private AtencionCita atencionCita;

    @OneToMany(mappedBy = "cita")
    private List<PQRS> listaPqrs;
}
