package co.edu.uniquindio.proyecto.modelo;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public class AtencionCita implements Serializable {

        @Lob
        @Id
        @Column(nullable = false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int codigo;

        @Lob
        @Column(nullable = true)
        private String diagnostico;

        @Lob
        @Column(nullable = false)
        private String tratamiento;

        @Lob
        @Column(nullable = false)
        private String notasMedicas;

        @OneToOne
        private Cita cita;

    }
