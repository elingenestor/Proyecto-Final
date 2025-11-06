package org.uniquindio.Service;

import org.uniquindio.Models.Estudiante;
import org.uniquindio.Utils.EmailValidador;
import org.uniquindio.Utils.ValidadorFecha;
import java.util.ArrayList;
import java.util.List;

public class EstudianteService {

    private List<Estudiante> estudiantes = new ArrayList<>();

    //METODO PARA DETERMINAR SI UN ESTUDIANTE ES VALIDO
    public boolean esEstudianteValido(Estudiante estudiante) {
        if (estudiante == null) return false;

        if (estudiante.getIdEstudiante() == null || estudiante.getIdEstudiante().isBlank())
            return false;
        if (estudiante.getNombres() == null || estudiante.getNombres().isBlank())
            return false;
        if (estudiante.getApellidos() == null || estudiante.getApellidos().isBlank())
            return false;
        if (!EmailValidador.esEmailValido(estudiante.getCorreoElectronico()))
            return false;
        if (!ValidadorFecha.fechaNoFutura(estudiante.getFechaIngreso()))
            return false;

        //CODIGO UNICO
        if (buscarPorId(estudiante.getIdEstudiante()) != null)
            return false;

        return true;
    }

    //BUSCAR ESTUDIANTE POR ID
    public Estudiante buscarPorId(String idEstudiante){
        return estudiantes.stream()
                .filter(estudiante -> estudiante.getIdEstudiante().equalsIgnoreCase(idEstudiante))
                .findFirst()
                .orElse(null);
    }

    //METODO PARA REGISTRAR ESTUDIANTE
    public boolean registrarEstudiante(Estudiante estudiante){
        if(esEstudianteValido(estudiante)){
            estudiantes.add(estudiante);
            return true;
        }
        return false;
    }
}
