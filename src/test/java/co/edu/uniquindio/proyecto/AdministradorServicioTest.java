package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.HorarioDTO;
import co.edu.uniquindio.proyecto.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;
import co.edu.uniquindio.proyecto.enumeraciones.Especialidad;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoPQRS;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoUsuario;
import co.edu.uniquindio.proyecto.modelo.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class AdministradorServicioTest {

    @Autowired
    private AdministradorServicios administradorServicios;

    @Test
    public void crearMedico() throws Exception {

        int codigoMedico = administradorServicios.crearMedico(new RegistroMedicoDTO("Alonso", "100365464", Ciudad.ARMENIA,
                Especialidad.PSICOLOGIA, "320464564", "alonso@gmail.com", "alonso123",
                "https://img.freepik.com/foto-gratis/doctora-vistiendo-bata-laboratorio-estetoscopio-aislado_1303-29791.jpg"
                , new ArrayList<>()));

        List<ItemMedicoDTO> lista = administradorServicios.listaMedicos();

        Assertions.assertFalse( !lista.isEmpty() );

    }

    @Test
    public void actualizarMedico() throws Exception {

        administradorServicios.actualizarMedico(new DetalleMedicoDTO(1, "Andres", "10023154",
                Ciudad.PEREIRA, Especialidad.PSICOLOGIA, "320164544", "Andres@gmail.com", "Url_Foto",
                new ArrayList<>()));


    }

    @Test
    public void eliminarMedico(int codigo) throws Exception {

        administradorServicios.eliminarMedico(1);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listaMedicos() throws Exception {

        List<ItemMedicoDTO> lista = administradorServicios.listaMedicos();
        Assertions.assertEquals(3, lista.size());

    }

   @Test
    public void obtenerMedico() throws Exception {

        administradorServicios.obtenerMedico(1);

    }

    @Test
    public void listarPQRS() throws Exception {

        administradorServicios.listarPQRS();

    }

    @Test
    public void verDetallePQRS() throws Exception {

       administradorServicios.verDetallePQRS(03);
    }

    @Test
    public void responderPQRS() throws Exception {

        administradorServicios.responderPQRS(new RegistroRespuestaDTO(05, 02, 03,
                01, "Debe acercarse a la sede mas cercana"));

    }

    @Test
    public void cambiarEstadoPQRS() throws Exception {

        administradorServicios.cambiarEstadoPQRS(01, EstadoPQRS.RESUELTO);
    }

    @Test
    public void listarCitas() throws Exception {

        administradorServicios.listarCitas();
    }

}
