package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.enumeraciones.Ciudad;
import co.edu.uniquindio.proyecto.enumeraciones.EPS;
import co.edu.uniquindio.proyecto.enumeraciones.Especialidad;
import co.edu.uniquindio.proyecto.enumeraciones.TipoSangre;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClinicaServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClinicaServicioImpl implements ClinicaServicio {


    @Override
    public List<Ciudad> listarCiudades() {
        return List.of( Ciudad.values() );
    }

    @Override
    public List<Especialidad> listarEspecialidades() {
        return List.of( Especialidad.values() );
    }

    @Override
    public List<TipoSangre> listarTiposSangre() {
        return null;
    }

    @Override
    public List<EPS> listarEps() {
        return null;
    }
}
