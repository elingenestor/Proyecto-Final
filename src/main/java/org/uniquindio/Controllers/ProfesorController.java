package org.uniquindio.Controllers;

import org.uniquindio.Models.Curso;
import org.uniquindio.Models.Profesor;
import org.uniquindio.Service.ProfesorService;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;

public class ProfesorController {
    private ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    // Registra profesor sin cursos (crea lista vacía)
    public void registrarProfesor(String idProfesor, String nombres, String apellidos, String correoElectronico, String materia) {
        List<Curso> cursosAsignados = new ArrayList<>();
        Profesor profesor = new Profesor(idProfesor, nombres, apellidos, correoElectronico, materia, cursosAsignados);
        service.registrarProfesor(profesor);
        System.out.println("Profesor registrado exitosamente");
    }

    // Si ya dispones de una lista de cursos
    public void registrarProfesor(String idProfesor, String nombres, String apellidos, String correoElectronico, String materia, List<Curso> cursosAsignados) {
        Profesor profesor = new Profesor(idProfesor, nombres, apellidos, correoElectronico, materia, cursosAsignados != null ? cursosAsignados : new ArrayList<>());
        service.registrarProfesor(profesor);
        System.out.println("Profesor registrado exitosamente");
    }

    public void listarProfesores() {
        List<Profesor> lista = service.listarProfesores();
        if (lista.isEmpty()) {
            System.out.println("No hay profesores registrados.");
        } else {
            System.out.println("\n === LISTA DE PROFESORES ===");
            lista.forEach(System.out::println);
        }
    }

    public void buscarProfesor(String idProfesor) {
        Profesor encontrado = service.buscarProfesor(idProfesor);
        if (encontrado != null) {
            System.out.println("Profesor encontrado: " + encontrado);
        } else {
            System.out.println("No se encontró el profesor con el id: " + idProfesor);
        }
    }

    public void actualizarProfesor(String idProfesor) {
        Scanner scanner = new Scanner(System.in);
        String id = idProfesor;
        if (id == null || id.isBlank()) {
            System.out.print("Ingrese el ID del profesor a actualizar: ");
            id = scanner.nextLine();
        }

        System.out.println("Dejar en blanco para no cambiar ese campo.");

        System.out.print("Nuevo nombre (o Enter para no cambiar): ");
        String nombre = scanner.nextLine();

        System.out.print("Nuevo apellido (o Enter para no cambiar): ");
        String apellido = scanner.nextLine();

        System.out.print("Nuevo correo (o Enter para no cambiar): ");
        String correo = scanner.nextLine();

        System.out.print("Nueva materia (o Enter para no cambiar): ");
        String materia = scanner.nextLine();

        // Mantener lista de cursos vacía al actualizar; el service debe aplicar la lógica para no sobrescribir si es null
        Profesor datosActualizados = new Profesor(
                id,
                nombre.isBlank() ? null : nombre,
                apellido.isBlank() ? null : apellido,
                correo.isBlank() ? null : correo,
                materia.isBlank() ? null : materia,
                new ArrayList<>()
        );

        boolean actualizado = service.actualizarProfesor(id, datosActualizados);
        if (actualizado) {
            System.out.println("✔ Profesor actualizado correctamente.");
        } else {
            System.out.println("❌ No se pudo actualizar el profesor.");
        }
    }

    public void eliminarProfesor(String idProfesor) {
        boolean eliminado = service.eliminarProfesor(idProfesor);
        if (eliminado) {
            System.out.println("Profesor eliminado exitosamente");
        } else {
            System.out.println("Profesor con este id no existe");
        }
    }
}
