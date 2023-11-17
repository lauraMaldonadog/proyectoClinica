package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.ItemPacienteDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;
import co.edu.uniquindio.proyecto.enumeraciones.EPS;
import co.edu.uniquindio.proyecto.enumeraciones.TipoSangre;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicios;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/clinica")
public class ClinicaController {

    @GetMapping("/lista-ciudades")
    public ResponseEntity<MensajeDTO> listarCiudades()throws Exception{
        System.out.println( "Ciudades "+ Ciudad.values() );
        return ResponseEntity.ok( new MensajeDTO( false, List.of( Ciudad.values()) ));
    }
    @GetMapping("/lista-eps")
    public ResponseEntity<MensajeDTO> listarEps()throws Exception{
        System.out.println( "EPS "+ EPS.values() );
        return ResponseEntity.ok( new MensajeDTO( false, List.of( EPS.values()) ));
    }

    @GetMapping("/lista-tipo-sangre")
    public ResponseEntity<MensajeDTO> listarTipoSangre()throws Exception{
        System.out.println( "Tipo Sangre "+ TipoSangre.values() );
        return ResponseEntity.ok( new MensajeDTO( false, List.of( TipoSangre.values()) ));
    }

}
