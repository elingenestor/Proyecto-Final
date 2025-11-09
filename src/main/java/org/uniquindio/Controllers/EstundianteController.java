package org.uniquindio.Controllers;

import org.uniquindio.Models.Estudiante;
import org.uniquindio.Service.EstudianteService;
import java.util.List;
import java.time.LocalDate;

public class EstundianteController {
    private EstudianteService service;

    //METODO CONSTRUCTOR
    public EstundianteController(EstudianteService service){
        this.service = service;
    }

    public void registrarEstudiante(String idEstudiante, String nombres, String apellidos, String correoElectronico, String numeroTelefono
    , LocalDate fechaNacimiento, String carrera, int SemestreActual,LocalDate fechaIngreso){
        Estudiante estudiante = new Estudiante(idEstudiante, nombres, apellidos,correoElectronico,numeroTelefono,fechaNacimiento,carrera, SemestreActual, fechaIngreso);
        service.registrarEstudiante(estudiante);
        System.out.println("Estudiante registrado exitosamente");
    }

    public void listarEstudiantes(){
        List<Estudiante> lista = service.listarEstudiantes();
        if (lista.isEmpty()){
            System.out.println("No hay estudiantes registrados.");
        } else {
            System.out.println("\n === lISTA DE ESTUDIANTES ===");
            lista.forEach(System.out::println);
        }
    }

    public void buscarEstudiante(String idEstudiante){
        List<Estudiante> lista = service.listarEstudiantes();
        Estudiante encontrado = service.buscarPorId(idEstudiante);
        if (encontrado != null){
            System.out.println("Estudiante encontrado: " +  encontrado);
        } else {
            System.out.println("No se encontro el estudiante con el id: " + idEstudiante);
        }
    }

    public void eliminarEstudiante(String idEstudiante){
        List<Estudiante> lista = service.listarEstudiantes();
        boolean eliminado = service.eliminarEstudiante(idEstudiante);
        if (eliminado){
            System.out.println("Estudiante eliminado exitosamente");
        } else {
            System.out.println("Estudiante con este id no existe");
        }
    }
}
