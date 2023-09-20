package co.edu.uniquindio.proyecto.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Mensaje implements Serializable {

    @Id
    private int codigo;
    private LocalDateTime fechaCreacion;
    private String mensaje;

    @ManyToOne
    private PQRS codigoPQRS;

    @ManyToOne
    private Cuenta codigoCuenta;

    @OneToOne
    private Mensaje codigoMensaje;
}


