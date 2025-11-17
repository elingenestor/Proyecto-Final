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
        if (buscarEstudiante(estudiante.getIdEstudiante()) != null)
            return false;

        return true;
    }

    // CREATE - METODO PARA REGISTRAR ESTUDIANTE
    public boolean registrarEstudiante(Estudiante estudiante){
        if(esEstudianteValido(estudiante)){
            estudiantes.add(estudiante);
            return true;
        }
        return false;
    }

    // READ - BUSCAR ESTUDIANTE POR ID
    public Estudiante buscarEstudiante(String idEstudiante){
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante estudiante = estudiantes.get(i);

            if(estudiante.getIdEstudiante().equals(idEstudiante)){
                return estudiante;
            }
        }
        return null;
    }

    // READ - LISTAR A TODOS LOS ESTUDIANTES
    public List<Estudiante> listarEstudiantes() {
        return new ArrayList<>(estudiantes); //Se retorna una copia para evitar modificaiones
    }

    //UPDATE - ACTUALIZAR DATOS DE UN ESTUDIANTE
    public boolean actualizarEstudiante(String idEstudiante, Estudiante datosActualizados) {
        Estudiante existente = buscarEstudiante(idEstudiante);
        if (existente == null) {
            System.out.println("Estudiante con ID: " + idEstudiante + "No encontrado.");
            return false;
        }

        //ACTUALIZAR SOLO LOS CAMPOS QUE NO SEAN NULOS O VACIOS
        if (datosActualizados.getNombres() != null && !datosActualizados.getNombres().isBlank())
            existente.setNombres(datosActualizados.getNombres());

        if (datosActualizados.getApellidos() != null && !datosActualizados.getApellidos().isBlank())
            existente.setApellidos(datosActualizados.getApellidos());

        if (datosActualizados.getCorreoElectronico() != null && EmailValidador.esEmailValido(datosActualizados.getCorreoElectronico()))
            existente.setCorreoElectronico(datosActualizados.getCorreoElectronico());


        if (datosActualizados.getNumeroTelefono() != null && !datosActualizados.getNumeroTelefono().isBlank())
            existente.setNumeroTelefono(datosActualizados.getNumeroTelefono());

        if (datosActualizados.getCarrera() != null && !datosActualizados.getCarrera().isBlank())
            existente.setCarrera(datosActualizados.getCarrera());

        if (datosActualizados.getSemestreActual() > 0)
            existente.setSemestreActual(datosActualizados.getSemestreActual());

        System.out.println("Estudiante actualizado correctamente.");
        return true;
    }

    //DELETE- METODO PARA ELIMINAR ESTUDIANTE
    public boolean eliminarEstudiante(String idEstudiante) {
        Estudiante estudiante = buscarEstudiante(idEstudiante);
        if (estudiante != null){
            estudiantes.remove(estudiante);
            System.out.println("Estudiante eliminado correctamente.");
            return true;
        }
        System.out.println("Estudiante no encontrado.");
        return false;
    }
}
