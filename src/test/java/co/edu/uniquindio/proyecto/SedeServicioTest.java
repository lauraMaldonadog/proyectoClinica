package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.enumeraciones.Sede;
import co.edu.uniquindio.proyecto.servicios.interfaces.SedesServicios;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class SedeServicioTest {

    @Autowired
    private SedesServicios sedesServicios;

    @Test
    public void listarSedes() {

        sedesServicios.listarSedes();


    }

    @Test
    public void elegirSede(String nombreSede) throws Exception{

        sedesServicios.elegirSede("Armenia");

        }
}
