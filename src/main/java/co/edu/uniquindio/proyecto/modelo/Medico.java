package co.edu.uniquindio.proyecto.modelo;



import co.edu.uniquindio.proyecto.dto.HorarioMedico;
import co.edu.uniquindio.proyecto.enumeraciones.Especialidad;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Medico extends Usuario implements Serializable {

    private Especialidad Especialidad;

    @OneToMany(mappedBy = "codigoMedico")
    private List<Horario> listaHorario;

    @OneToMany(mappedBy = "codMedico")
    private List<Cita> listaCitas;

    @OneToMany(mappedBy = "codigoMedico")
    private List<DiaLibre> diaLibres;

}

