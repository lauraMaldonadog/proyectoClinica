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
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
    private final PacienteServicios pacienteServicio;



    @PostMapping("/registrarse")
    public ResponseEntity<MensajeDTO<String>> registrarse(@Valid @RequestBody RegistroPacienteDTO pacienteDTO) throws Exception{
        pacienteServicio.registrarse(pacienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente registrado correctamente") );
    }
    @PutMapping("/editar-perfil")
    public int editarPerfil(@Valid @RequestBody DetallePacienteDTO pacienteDTO) throws Exception{
        return pacienteServicio.editarPerfil(pacienteDTO);
    }
    @DeleteMapping("/eliminar/{codigo}")
    public void eliminarCuenta(@PathVariable int codigo) throws Exception{
        pacienteServicio.eliminarCuenta(codigo);
    }
    @GetMapping("/detalle/{codigo}")
    public DetallePacienteDTO verDetallePaciente(@PathVariable int codigo) throws Exception{
        return pacienteServicio.verDetallePaciente(codigo);
    }
    @GetMapping("/listar-todos")
    public List<ItemPacienteDTO> listarTodos(){
        return pacienteServicio.listarTodos();
    }

}
