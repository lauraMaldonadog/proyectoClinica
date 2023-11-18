package co.edu.uniquindio.proyecto.controladores;
import co.edu.uniquindio.proyecto.dto.AtencionCitaDTOMedico;
import co.edu.uniquindio.proyecto.dto.CitaDTOMedico;
import co.edu.uniquindio.proyecto.dto.DiaLibreDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.MedicoServicios;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MedicoController {
    private final MedicoServicios medicoServicio;

    @GetMapping("/listar-citas-pendientes")
    public ResponseEntity<MensajeDTO<List<CitaDTOMedico>>> listarCitasPendientes()throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, medicoServicio.citasPendientes()) );
    }

    @PutMapping("/atender-cita")
    public ResponseEntity<MensajeDTO<String>> atenderCita(@Valid @RequestBody AtencionCitaDTOMedico atencionCitaMedicoDTO) throws Exception{
        medicoServicio.atenderCitas(atencionCitaMedicoDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "La cita ha sido atendida correctamente" ));
    }

    @PostMapping("/agendar-dia-libre")
    public ResponseEntity<MensajeDTO<String>> agendarDiaLibre(@Valid @RequestBody DiaLibreDTO diaLibreDTO) throws Exception{
        medicoServicio.agendarDiaLibre(diaLibreDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Dia libre agendado con exito"));
    }

    @GetMapping("/listar-citas-pacientes")
    public ResponseEntity<MensajeDTO<List<CitaDTOMedico>>> listarCitasPacientes() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, medicoServicio.listarCitasPacientes()));
    }

    @GetMapping("/listar-citas-realizadas")
    public ResponseEntity<MensajeDTO<List<CitaDTOMedico>>> listarCitasRealizadasMedico() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, medicoServicio.listarCitasRealizadasMedico()));
    }
}
