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
        private int codigo;

        @Lob
        @Column(nullable = false)
        private String diagnotisco;

        @Lob
        @Column(nullable = false)
        private String tratamiento;

        @Lob
        @Column(nullable = false)
        private String notasMedicas;

        @OneToOne
        private Cita cita;

    }
