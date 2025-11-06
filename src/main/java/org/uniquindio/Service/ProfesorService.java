package org.uniquindio.Service;

import org.uniquindio.Models.Profesor;
import org.uniquindio.Utils.EmailValidador;
import org.uniquindio.Utils.ValidadorFecha;
import java.util.ArrayList;
import java.util.List;

public class ProfesorService {

    private List<Profesor> profesores = new ArrayList<>();

    //METODO PARA DETERMINAR SI UN PROFESOR ES VALIDO
    public boolean esProfesorValido(Profesor profesor) {
        if (profesor == null)
            return false;


        if (profesor.getIdProfesor() == null || profesor.getIdProfesor().isBlank())
            return false;
        if (profesor.getNombres() == null || profesor.getNombres().isBlank())
            return false;
        if (profesor.getApellidos() == null || profesor.getApellidos().isBlank())
            return false;
        if (profesor.getMateria() == null || profesor.getMateria().isBlank())
            return false;
        if (!EmailValidador.esEmailValido(profesor.getCorreoElectronico()))
            return false;

        //CODIGO UNICO
        if(buscarPorId(profesor.getIdProfesor()) != null)
            return false;

        //VALIDAR QUE NO ESTE ASOGNADO DOS VECES AL MISMO CURSO
        if (profesor.getCursosAsignados().stream().distinct().count() != profesor.getCursosAsignados().size())
            return false;

        return true;
    }

    //BUSCAR PROFESOR POR ID
    public Profesor buscarPorId(String idProfesor) {
        return profesores.stream()
                .filter(profesor -> profesor.getIdProfesor().equalsIgnoreCase(idProfesor))
                .findFirst()
                .orElse(null);
    }


    //METODO PARA REGISTRAR PROFESOR
    public boolean registrarProfesor(Profesor profesor) {
        if(esProfesorValido(profesor)) {
            profesores.add(profesor);
            return true;
        }
        return false;
    }
}
