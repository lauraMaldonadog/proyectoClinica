package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.AtencionCitaDTOMedico;
import co.edu.uniquindio.proyecto.dto.CitaDTOMedico;
import co.edu.uniquindio.proyecto.dto.DiaLibreDTO;
import co.edu.uniquindio.proyecto.dto.ItemPacienteDTO;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoCita;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MedicoServicioTest {

    @Autowired
    private MedicoServicios medicoServicios;

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasPendientesMedicoTest() throws Exception {

        int codigoMedico = 1;
        // Llama al método listarCitasPendientesMedico con el código de médico
        List<CitaDTOMedico> lista = medicoServicios.citasPendientes(codigoMedico);

        assertNotNull(lista);
        assertEquals(0, lista.size());
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void atenderCitas() throws Exception {
        // Supongamos que tienes un objeto AtencionCitaDTOMedico válido llamado atencionCitaDTO
        AtencionCitaDTOMedico atencionCitaDTO = new AtencionCitaDTOMedico(2, "No tiene nada",
                "Dormir", "El paciente necesita mimir");

        // Llama al método atenderCitas con el objeto atencionCitaDTO
        assertDoesNotThrow(() -> medicoServicios.atenderCitas(atencionCitaDTO));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasRealizadasTest() throws Exception {
//Obtenemos la lista de todos los pacientes
        List<CitaDTOMedico> lista = medicoServicios.listarCitasRealizadasMedico();
        for (CitaDTOMedico cita : lista) {
            System.out.println(cita); // Corrección aquí
        }
        assertEquals(1, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasPacientes() throws Exception {
//Obtenemos la lista de todos los pacientes
        List<CitaDTOMedico> lista = medicoServicios.listarCitasPacientes();

        for (CitaDTOMedico cita : lista) {
            System.out.println(cita);
        }
        assertNotNull(lista);
        assertEquals(5, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void agendarDiaLibreTest() throws Exception {
        // Supongamos que tienes un objeto DiaLibreDTO válido llamado diaLibreDTO
        DiaLibreDTO diaLibreDTO = new DiaLibreDTO(1, LocalDate.of(2023, 11, 15));

        // Llama al método agendarDiaLibre con el objeto diaLibreDTO
        String resultado = medicoServicios.agendarDiaLibre(diaLibreDTO);

        assertEquals("Fecha programada", resultado);
    }

}