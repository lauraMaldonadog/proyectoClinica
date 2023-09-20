package co.edu.uniquindio.proyecto.modelo;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Getter
@Setter

@AllArgsConstructor
public class Administrador extends Cuenta implements Serializable {


}
