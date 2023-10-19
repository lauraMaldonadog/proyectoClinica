package co.edu.uniquindio.proyecto.modelo;



import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;
import co.edu.uniquindio.proyecto.enumeraciones.EPS;
import co.edu.uniquindio.proyecto.enumeraciones.TipoSangre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Paciente extends Usuario implements Serializable {

        private LocalDate fechaNacimiento;

        private String alergias;

        private EPS codigoEps;

        private TipoSangre codigoTipoSangre;

        @OneToMany(mappedBy = "paciente")
        private List<Cita> listaCitas;

    }


