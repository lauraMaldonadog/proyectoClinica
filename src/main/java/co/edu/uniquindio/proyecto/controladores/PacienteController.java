package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.ItemPacienteDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicios;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pacientes")
public class PacienteController {

    private final PacienteServicios pacienteServicios;

    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO> registro(@Valid @RequestBody RegistroPacienteDTO registroPacienteDTO)throws Exception{
        pacienteServicios.registrarse(registroPacienteDTO);
        return ResponseEntity.ok( new MensajeDTO(false, "Registro correcto")) ;
    }

    @PutMapping("/editar-perfil")
    public int editarPerfil(@Valid @RequestBody DetallePacienteDTO detallePacienteDTO) throws Exception{
        return 0;
    }

    @DeleteMapping("/eliminar/{codigo}")
    public void eliminarCuenta(@PathVariable int codigo) throws Exception{
    }

    @GetMapping ("/detaller/{codigo})")
    public DetallePacienteDTO verDetallePaciente(@PathVariable int codigo) throws Exception{
        return null;
    }

    @GetMapping("/listar-pacientes")
    public List<ItemPacienteDTO> listarTodos(){
        return null;
    }

}
