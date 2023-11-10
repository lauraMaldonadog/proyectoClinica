package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.AtencionCitaDTOMedico;
import co.edu.uniquindio.proyecto.dto.CitaDTOMedico;
import co.edu.uniquindio.proyecto.modelo.Cita;
import co.edu.uniquindio.proyecto.repositorios.AtencionCitaRepo;
import co.edu.uniquindio.proyecto.repositorios.CitaRepository;
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
    public void listarCitasTest() throws Exception {
        List<CitaDTOMedico> lista = medicoServicios.citasPendientes();
        lista.forEach(System.out::println);
        Assertions.assertEquals(0, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    void atenderCitas(AtencionCitaDTOMedico atencionCitaDTOMedico) throws Exception{
        medicoServicios.atenderCitas(new AtencionCitaDTOMedico(1,"Pie de atleta", "acetaminofen","Cuidese mucho "));
    }
}
