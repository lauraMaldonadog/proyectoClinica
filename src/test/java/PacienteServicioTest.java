import co.edu.uniquindio.proyecto.dto.CitaDTOPaciente;
import co.edu.uniquindio.proyecto.dto.ItemPacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;
import co.edu.uniquindio.proyecto.enumeraciones.EPS;
import co.edu.uniquindio.proyecto.enumeraciones.TipoSangre;
import co.edu.uniquindio.proyecto.modelo.Cita;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class PacienteServicioTest {
    @Autowired
    private PacienteServicios pacienteServicio;

    @Test
    public void registrarTest() throws Exception {
//Creamos un objeto con los datos del paciente
        RegistroPacienteDTO pacienteDTO = new RegistroPacienteDTO(
                "1097222222",
                "Pepito Perez",
                "3243434",
                "aquí va la url de la foto",
                Ciudad.ARMENIA,
                LocalDate.of(1990, 10, 7),
                "El polvo y el polen me hacen estornudar",
                EPS.NUEVA_EPS,
                TipoSangre.A_POSITIVO,
                "pepitoperez@email.com",
                "12345678");

//Guardamos el registro usando el método del servicio
        int nuevo = pacienteServicio.registrarse(pacienteDTO);
//Comprobamos que sí haya quedado guardado. Si se guardó debe retornar el código (no 0).

        Assertions.assertNotEquals(0, nuevo);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarTest() throws Exception {
//Para actualizar el paciente primero lo obtenemos
        DetallePacienteDTO guardado = pacienteServicio.verDetallePaciente(1);
//Le modificamos el número de teléfono, lo demás lo dejamos igual
        DetallePacienteDTO modificado = new DetallePacienteDTO(
                guardado.codigo(),
                guardado.cedula(),
                guardado.nombre(),
                "111111",
                guardado.urlFoto(),
                guardado.ciudad(),
                guardado.fechaNacimiento(),
                guardado.alergias(),
                guardado.eps(),
                guardado.tipoSangre(),
                guardado.correo());
//Se invoca el servicio de actualizar los datos
        pacienteServicio.editarPerfil(modificado);
//Se obtiene nuevamente el paciente para comprobar que sí se haya actualizado
        DetallePacienteDTO objetoModificado = pacienteServicio.verDetallePaciente(1);
//Se comprueba que el teléfono del paciente sea el que se le asignó en la actualización
        Assertions.assertEquals("111111", objetoModificado.telefono());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTest() throws Exception {
        // Obtener el DetallePacienteDTO del paciente que deseas eliminar
        DetallePacienteDTO pacienteAEliminar = pacienteServicio.verDetallePaciente(1);

        // Verificar que el paciente se ha encontrado antes de intentar eliminarlo
        Assertions.assertNotNull(pacienteAEliminar);

        // Eliminar el paciente
        pacienteServicio.eliminarCuenta(pacienteAEliminar);

        // Verificar que el paciente se ha eliminado
        Assertions.assertThrows(Exception.class, () -> pacienteServicio.verDetallePaciente(1));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTest() {
//Obtenemos la lista de todos los pacientes
        List<ItemPacienteDTO> lista = pacienteServicio.listarTodos();
        for (ItemPacienteDTO paciente : lista) {
            System.out.println(paciente); // Corrección aquí
        }
//Si en el dataset creamos 2 pacientes, entonces el tamaño de la lista debe ser 2
        Assertions.assertEquals(2, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public List<CitaDTOPaciente> filtrarCitasPorFecha() throws Exception {

        String fecha1 = "2023-11-15T10:30:00";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime fechaF1 = LocalDateTime.parse(fecha1, formatter1);

        String fecha2 = "2023-11-15T10:30:00";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime fechaF2 = LocalDateTime.parse(fecha1, formatter2);

        String fecha3 = "2023-11-15T10:30:00";
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime fechaF3 = LocalDateTime.parse(fecha1, formatter3);

        String fechaBuscar = "2023-10-16T12:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime fechaDesdeTexto = LocalDateTime.parse(fechaBuscar, formatter);

        CitaDTOPaciente c1 = new CitaDTOPaciente(01,"Pedro", fechaF1, "Dolor de cabeza");

        CitaDTOPaciente c2 = new CitaDTOPaciente(02,"Andres", fechaF2, "Dolor de panza");

        CitaDTOPaciente c3 = new CitaDTOPaciente(03,"Andrea", fechaF3, "Dolor de pierna");



        return null;
    }

}

