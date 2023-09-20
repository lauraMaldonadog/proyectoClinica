package co.edu.uniquindio.proyecto.modelo;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class Cuenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    @Lob
    private int codigo;

    @Column(nullable = false, unique = true, length = 40)
    private String correo;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "codigoCuenta")
    private List<Mensaje> mensajes;

}


