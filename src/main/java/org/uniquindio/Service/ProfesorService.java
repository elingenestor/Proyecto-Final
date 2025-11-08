package org.uniquindio.Service;

import org.uniquindio.Models.Profesor;
import org.uniquindio.Utils.EmailValidador;
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

    //CREATE - METODO PARA REGISTRAR PROFESOR
    public boolean registrarProfesor(Profesor profesor) {
        if(esProfesorValido(profesor)) {
            profesores.add(profesor);
            System.out.println("Profesor agregado correctamente: " + profesor.getNombres());
            return true;
        }
        System.out.println("No se pudo registrar el profesor. Datos invalidos o duplicados");
        return false;
    }

    //READ- BUSCAR PROFESOR POR ID
    public Profesor buscarPorId(String idProfesor) {
        return profesores.stream()
                .filter(profesor -> profesor.getIdProfesor().equalsIgnoreCase(idProfesor))
                .findFirst()
                .orElse(null);
    }

    //READ - LISTAR TODOS LOS PROFESORES
    public List<Profesor> listarProfesores() {
        return new ArrayList<>(profesores);
    }

    //UPDATE - ACTUALIZAR LOS DATOS DE UN PROFESOR
    public boolean actualizarProfesor(String idProfesor, Profesor datosNuevos) {
        Profesor existente = buscarPorId(idProfesor);
        if (existente == null){
            System.out.println("ID del Profesor: " + idProfesor + "no encontrado.");
            return false;
        }
    //ACTUALIZAR SOLO LOS CAMPOS VACIOS O NULOS
        if (datosNuevos.getNombres() != null && !datosNuevos.getNombres().isBlank())
            existente.setNombres(datosNuevos.getNombres());

        if (datosNuevos.getApellidos() != null && !datosNuevos.getApellidos().isBlank())
            existente.setApellidos(datosNuevos.getApellidos());

        if (datosNuevos.getCorreoElectronico() != null && EmailValidador.esEmailValido(datosNuevos.getCorreoElectronico()))
            existente.setCorreoElectronico(datosNuevos.getCorreoElectronico());

        if (datosNuevos.getMateria() != null && !datosNuevos.getMateria().isBlank())
            existente.setMateria(datosNuevos.getMateria());

        if (datosNuevos.getCursosAsignados() != null && !datosNuevos.getCursosAsignados().isEmpty()){
            //EVITAR DUPLICADOS EN LA LISTA
            boolean sinDuplicados = datosNuevos.getCursosAsignados().stream().distinct().count()
                    == datosNuevos.getCursosAsignados().size();
            if (sinDuplicados){
                existente.setCursosAsignados(datosNuevos.getCursosAsignados());
            } else {
                System.out.println("No se actualizaron los cursos asignados debido a duplicados.");
            }
        }

        System.out.println("Profesor con ID: " + idProfesor + " actualizado correctamente.");
        return true;
    }

    //DELETE - METODO PARA ELIMINAR PROFESOR
    public boolean eliminarProfesor(String idProfesor) {
        Profesor profesor = buscarPorId(idProfesor);
        if (profesor != null) {
            profesores.remove(profesor);
            System.out.println("Profesor eliminado correctamente.");
            return true;
        }
        System.out.println("Profesor no encontrado.");
        return false;
    }
}
