package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.CitaDTOMedico;
import co.edu.uniquindio.proyecto.modelo.Cita;
import co.edu.uniquindio.proyecto.repositorios.AtencionCitaRepo;
import co.edu.uniquindio.proyecto.repositorios.CitaRepository;
import co.edu.uniquindio.proyecto.servicios.impl.MedicoServicioImpl;
import co.edu.uniquindio.proyecto.servicios.interfaces.MedicoServicios;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class MedicoServicioTest {
    @Autowired
    private MedicoServicios medicoServicios;
    @Test
    @Sql("classpath:dataset.sql")
    public void citasPendientes() throws Exception {
        List<CitaDTOMedico> listarCitasPendientes = medicoServicios.citasPendientes();
        Assertions.assertEquals(2,listarCitasPendientes.size());
    }

/*
Funciona
 */
    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasPacientes() throws Exception{
        List<CitaDTOMedico> listarPacientes = medicoServicios.listarCitasPacientes();
        Assertions.assertEquals(5, listarPacientes.size());

    }




}
