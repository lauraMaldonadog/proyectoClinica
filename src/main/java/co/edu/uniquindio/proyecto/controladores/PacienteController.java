package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.proyecto.modelo.PQRS;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicios;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
    private final PacienteServicios pacienteServicio;




    @PutMapping("/editar-perfil")
    public ResponseEntity<MensajeDTO<String>> editarPerfil(@Valid @RequestBody DetallePacienteDTO pacienteDTO) throws Exception {
        pacienteServicio.editarPerfil(pacienteDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Paciente actualizado correctamente"));
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable int codigo) throws
            Exception {
        pacienteServicio.eliminarCuenta(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Paciente eliminado correctamete")
        );
    }

    @GetMapping("/detalle/{codigo}")
    public ResponseEntity<MensajeDTO<DetallePacienteDTO>> verDetallePaciente(@PathVariable int codigo) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false,
                pacienteServicio.verDetallePaciente(codigo)));
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<MensajeDTO<List<ItemPacienteDTO>>> listarTodos() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, pacienteServicio.listarTodos()));
    }



    @PostMapping("agendar-cita")
    public ResponseEntity<MensajeDTO<String>> agendarCita(@Valid @RequestBody CitaDTOPaciente cita) throws Exception {

        pacienteServicio.agendarCita(cita);

        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Cita agendada correctamente"));
    }

    @PostMapping("crear-pqrs")
    public ResponseEntity<MensajeDTO<String>> crearPQRS(@Valid @RequestBody DetallePQRSDTO pqrsdto) throws Exception {

        pacienteServicio.crearPQRS(pqrsdto);

        return ResponseEntity.ok().body(new MensajeDTO<>(false, "PQRS agendada correctamente"));
    }

    /*@PostMapping("responder-pqrs")
    public ResponseEntity<MensajeDTO<RegistroRespuestaDTO>> responderPQRS(@Valid @RequestBody RegistroRespuestaDTO respuestaDTO) throws Exception {

        return ResponseEntity.ok().body(new MensajeDTO<>(false, pacienteServicio.responderPQRS(respuestaDTO)));
    }*/

    @GetMapping("listar-citas/{codigo}")
    public ResponseEntity<MensajeDTO<List<CitaDTOPaciente>>> listarCitas(@PathVariable int codigo) throws Exception {

        return ResponseEntity.ok().body(new MensajeDTO<>(false, pacienteServicio.listarCitasPaciente(codigo)));
    }

    @GetMapping("/filtrar-citas-por-medico/{nombreMedico}")
    public ResponseEntity<List<CitaDTOPaciente>> filtrarCitasPorMedico(@PathVariable String nombreMedico) throws Exception {
        List<CitaDTOPaciente> citas = pacienteServicio.filtrarCitasPorMedico(nombreMedico);
        return ResponseEntity.ok().body(citas);
    }



    @GetMapping("/filtrar-citas-por-fecha/{fecha}")
    public ResponseEntity<List<CitaDTOPaciente>> filtrarCitasPorFecha(@PathVariable String fecha) throws Exception {
        // Convierte la cadena de fecha a LocalDateTime, ajusta el formato según tus necesidades
        LocalDateTime fechaFiltrar = LocalDateTime.parse(fecha);

        List<CitaDTOPaciente> citas = pacienteServicio.filtrarCitasPorFecha(fechaFiltrar);
        return ResponseEntity.ok().body(citas);
    }




}