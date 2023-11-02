package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.AgendarDiaLibreDTO;
import co.edu.uniquindio.proyecto.dto.AtencionCitaDTOMedico;
import co.edu.uniquindio.proyecto.dto.CitaDTOMedico;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoCita;
import co.edu.uniquindio.proyecto.modelo.AtencionCita;
import co.edu.uniquindio.proyecto.modelo.Cita;
import co.edu.uniquindio.proyecto.modelo.DiaLibre;
import co.edu.uniquindio.proyecto.modelo.Medico;
import co.edu.uniquindio.proyecto.repositorios.AtencionCitaRepo;
import co.edu.uniquindio.proyecto.repositorios.CitaRepository;
import co.edu.uniquindio.proyecto.repositorios.DiaLibreRepository;
import co.edu.uniquindio.proyecto.repositorios.MedicoRepository;
import co.edu.uniquindio.proyecto.servicios.interfaces.MedicoServicios;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicoServicioImpl implements MedicoServicios {

    private final CitaRepository citaRepo;
    private final AtencionCitaRepo atencionCitaRepo;
    private final MedicoRepository medicoRepository;
    private final DiaLibreRepository diaLibreRepository;

    @Override
    public List<CitaDTOMedico> citasPendientes() throws Exception {         // lista de citas para
        List<Cita> citas = citaRepo.findAll();                              // atender el mismo dia
        List<CitaDTOMedico> listaCitasMedicoDia = new ArrayList<>();

        if (citas.isEmpty()) {
            throw new Exception("No hay citas para hoy");
        }

        for (Cita c : citas) {
            if (c.getFechaCita().isAfter(LocalDateTime.now())) {          // Fecha
                listaCitasMedicoDia.add(new CitaDTOMedico(
                        c.getCodigo(),
                        c.getPaciente().getNombre(),
                        c.getFechaCita(),
                        c.getMotivo()
                ));
            }
        }

        return listaCitasMedicoDia;
    }


    /*
    Atender citas del dia
    funciona
     */
    @Override
    public void atenderCitas(AtencionCitaDTOMedico atencionCitaDTOMedico) throws Exception {                   //Atender cita
        Optional<Cita> optionalCita = citaRepo.findById(atencionCitaDTOMedico.codigoCita());
        if (optionalCita.isEmpty()) {
            throw new Exception("No hay cita con el codigo:" + atencionCitaDTOMedico.codigoCita());
        }
        AtencionCita atencionCita = new AtencionCita();
        atencionCita.setCita(optionalCita.get());
        atencionCita.setTratamiento(atencionCita.getTratamiento());
        atencionCita.setDiagnotisco(atencionCita.getDiagnotisco());
        atencionCita.setNotasMedicas(atencionCita.getNotasMedicas());
        atencionCita.getCita().setEstadoCita(EstadoCita.ATENDIDA);
        atencionCitaRepo.save(atencionCita);
    }

    /*
    Visualizar las citas futuras
    Funcina Comprobado
     */

    public List<CitaDTOMedico> listarCitasPacientes() throws Exception {
        List<Cita> citas = citaRepo.findAll();
        List<CitaDTOMedico> listaCitasMedicoFuturas = new ArrayList<>();

        if (citas.isEmpty()) {
            throw new Exception("No hay citas programadas");
        }
        for (Cita c : citas) {
            listaCitasMedicoFuturas.add(new CitaDTOMedico(
            c.getCodigo(),
            c.getPaciente().getNombre(),
            c.getFechaCita(),
            c.getMotivo()));
        }
        return listaCitasMedicoFuturas;
    }

    /*
    Agendar el dia libre para el medico
          Modificacion Rojo

     */
    public String agendarDiaLibre(AgendarDiaLibreDTO dto) throws Exception {

        Optional<Medico> optionalMedico = medicoRepository.findById(dto.codigoMedico());

        if(optionalMedico.isEmpty()){
            throw new Exception("No existe un médico con el código enviado");
        }

        List<Cita> citaList = citaRepo.findAll();
        for (Cita l : citaList) {
            if(l.getMedico().getCodigo() == dto.codigoMedico()) {
                if (LocalDate.from(l.getFechaCita()).equals(dto.diaLibre())) {
                    throw new Exception("Esta fecha tiene programado citas.\nSeleccione otra fecha para descanso");
                }
            }

        }

        DiaLibre diaLibre1 = new DiaLibre();
        diaLibre1.setDia(dto.diaLibre());
        diaLibre1.setCodigoMedico( optionalMedico.get() );

        diaLibreRepository.save(diaLibre1);

        return "Fecha programada";
    }

    /*
    Lista de las citas atendidas por el medico
     */
    @Override
    public List<CitaDTOMedico> listarCitasRealizadasMedico() throws Exception {
        List<Cita> listaCitasAtendidas = citaRepo.findAll();
        List<CitaDTOMedico> listaAtendida = new ArrayList<>();
        if (listaCitasAtendidas.isEmpty()) {
            throw new Exception("No tiene citas");
        }

        for (Cita c : listaCitasAtendidas) {
            if (c.getEstadoCita().equals(EstadoCita.ATENDIDA)) {
                listaAtendida.add(new CitaDTOMedico(c.getCodigo(),
                        c.getPaciente().getNombre(),
                        c.getFechaCita(),
                        c.getMotivo()));
            }
        }
        return listaAtendida;
    }


}

