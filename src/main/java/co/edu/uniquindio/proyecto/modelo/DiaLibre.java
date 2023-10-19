package co.edu.uniquindio.proyecto.modelo;

    import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public class DiaLibre {
        @Id
        @EqualsAndHashCode.Include
        private int codigo;

        private LocalDate dia;

        @ManyToOne
        private Medico codigoMedico;

    }


