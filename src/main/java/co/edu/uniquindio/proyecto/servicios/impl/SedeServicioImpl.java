package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.enumeraciones.Sede;
import co.edu.uniquindio.proyecto.modelo.Cita;
import co.edu.uniquindio.proyecto.repositorios.SedeRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.SedesServicios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SedeServicioImpl implements SedesServicios {

    private final SedeRepo sedeRepo;
    @Override
    public void listarSedes() {

        List<Sede> listaSedes = sedeRepo.findAll();


    }

    @Override
    public String elegirSede(String nombreSede) throws Exception{

        Sede encontrado = sedeRepo.findByNombre(nombreSede);

        if (encontrado != null){

            return encontrado.getNombre();

        }else {

            throw new Exception("El nombre de la sede es incorrecto");

        }

    }
}
