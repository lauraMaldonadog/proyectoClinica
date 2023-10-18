package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.AtencionCitaDTOMedico;
import co.edu.uniquindio.proyecto.dto.CitaDTOMedico;
import co.edu.uniquindio.proyecto.enumeraciones.EstadoCita;
import co.edu.uniquindio.proyecto.modelo.AtencionCita;
import co.edu.uniquindio.proyecto.modelo.Cita;
import co.edu.uniquindio.proyecto.modelo.Medico;
import co.edu.uniquindio.proyecto.repositorios.AtencionCitaRepo;
import co.edu.uniquindio.proyecto.repositorios.CitaRepository;
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

    @Override
    public List<CitaDTOMedico> citasPendientes() throws Exception {         // lista de citas para hoy
        List<Cita> citas = citaRepo.findAll();
        List<CitaDTOMedico> listaCitasMedicoDia= new ArrayList<>();

        if (citas.isEmpty()){
            throw new Exception("No hay citas para hoy");
        }

        for (Cita c: citas) {
            if (c.getFechaCita().isAfter( LocalDateTime.now() )) {
                listaCitasMedicoDia.add( new CitaDTOMedico(
                        c.getCodigo(),
                        c.getPaciente().getNombre(),
                        c.getFechaCita(),
                        c.getMotivo()
                )  );
            }
        }

        return listaCitasMedicoDia;
    }



    @Override
    public void atenderCitas(AtencionCitaDTOMedico atencionCitaDTOMedico) throws Exception {                   //Atender cita
        Optional<Cita> optionalCita = citaRepo.findById(atencionCitaDTOMedico.codigoCita());
        if (optionalCita.isEmpty()){
            throw new Exception("No hay cita con el codigo:"+atencionCitaDTOMedico.codigoCita());
        }

        AtencionCita atencionCita = new AtencionCita();
        atencionCita.setCita( optionalCita.get() );
        atencionCita.setTratamiento(atencionCita.getTratamiento());
        atencionCita.setDiagnotisco(atencionCita.getDiagnotisco());
        atencionCita.setNotasMedicas(atencionCita.getNotasMedicas());
        atencionCita.getCita().setEstadoCita(EstadoCita.ATENDIDA);
        atencionCitaRepo.save(atencionCita);

    }


    public List<CitaDTOMedico> listarCitasPacientes() throws Exception {          // lista de citas futuras
        List<Cita> citas = citaRepo.findAll();
        List<CitaDTOMedico> listaCitasMedicoFuturas= new ArrayList<>();
        Cita horaCita=null;

        if (citas.isEmpty()){
            throw new Exception("No hay citas programadas");
        }
        for (Cita c: citas) {
            c.getCodigo();
            c.getPaciente().getNombre();
            c.getFechaCita();
            c.getMotivo();
        }
        return listaCitasMedicoFuturas;
    }

    public String agendarDiaLibre(CitaDTOMedico cita) throws Exception{
        Cita listaCitas;
        List<Cita> citaList = citaRepo.findAll();
        LocalDate fechaReceso = LocalDate.of(2023,11,30);
        LocalDate fechaHoy = LocalDate.now();
        if (fechaReceso.equals(fechaHoy)){
            throw new Exception("Hoy no puede solicitar receso.\nTiene que solicitar con anticipacion.");
        }
        for (Cita l : citaList){
            if (!l.getFechaCita().equals(fechaReceso)){
                throw new Exception("Esta fecha tiene programado citas.\nSeleccione otra fecha para descanso");
            }
        }
        return "Fecha programada";
    }

    @Override
    public List<CitaDTOMedico> listarCitasRealizadasMedico() throws Exception{
        List<Cita> listaMedico = citaRepo.findAll();
        if (listaMedico.isEmpty()){
            throw new Exception("No tiene citas atendidas");
        }
        List<CitaDTOMedico> listaAtendida = new ArrayList<>();




        /*for (CitaDTOMedico c : listaMedico ) {
            if (c.getEstadoCita().equals(EstadoCita.ATENDIDA || EstadoCita.CANCELADA)){
                listaAtendida.add(c);
            }
        }*/

        return listaAtendida;
    }


}

