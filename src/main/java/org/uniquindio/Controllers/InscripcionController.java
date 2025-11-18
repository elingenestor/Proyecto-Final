package org.uniquindio.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.uniquindio.Models.Curso;
import org.uniquindio.Service.InscripcionService;
import org.uniquindio.Models.Inscripcion;
import org.uniquindio.Models.Estudiante;

public class InscripcionController {
    private final InscripcionService inscripcionService;
    private final Scanner scanner;

    public InscripcionController(List<Curso> cursos, List<Estudiante> estudiantes, List<Inscripcion> inscripciones) {
        this.inscripcionService = new InscripcionService(cursos, estudiantes, inscripciones);
        this.scanner = new Scanner(System.in);
    }

    // 1. REGISTRAR INSCRIPCIÓN

    private void registrarInscripcion() {
        System.out.println("\n--- Registrar Inscripción ---");

        System.out.print("ID del estudiante: ");
        String idEst = scanner.nextLine();

        System.out.print("ID del curso: ");
        String idCur = scanner.nextLine();

        Inscripcion nueva = new Inscripcion();
        nueva.setIdEstudiante(idEst);
        nueva.setCursoId(idCur);

        boolean ok = inscripcionService.registrarInscripcion(nueva);

        if (ok) System.out.println("Inscripción registrada.");
        else System.out.println("No se pudo registrar.");
    }


    // 2. LISTAR TODAS

    private void listarInscripciones() {
        System.out.println("\n--- Lista de Inscripciones ---");

        List<Inscripcion> lista = inscripcionService.listarInscripciones();

        if (lista.isEmpty()) {
            System.out.println("No hay inscripciones registradas.");
            return;
        }

        for (Inscripcion i : lista) {
            System.out.println("Estudiante: " + i.getIdEstudiante() +
                    " | Curso: " + i.getCursoId());
        }
    }


    // 3. BUSCAR POR ID

    private void buscarInscripcion() {
        System.out.println("\n--- Buscar Inscripción ---");

        System.out.print("ID del estudiante: ");
        String idEst = scanner.nextLine();

        System.out.print("ID del curso: ");
        String idCur = scanner.nextLine();

        Inscripcion ins = inscripcionService.buscarPorId(idEst, idCur);

        if (ins == null) {
            System.out.println("No se encontró esa inscripción.");
        } else {
            System.out.println("Inscripción encontrada: ");
            System.out.println("Estudiante: " + ins.getIdEstudiante());
            System.out.println("Curso: " + ins.getCursoId());
        }
    }


    // 4. ACTUALIZAR INSCRIPCIÓN

    private void actualizarInscripcion() {
        System.out.println("\n--- Actualizar Inscripción ---");

        System.out.print("ID actual del estudiante: ");
        String idEstActual = scanner.nextLine();

        System.out.print("ID actual del curso: ");
        String idCurActual = scanner.nextLine();

        Inscripcion existente = inscripcionService.buscarPorId(idEstActual, idCurActual);

        if (existente == null) {
            System.out.println("Inscripción no encontrada.");
            return;
        }

        System.out.print("Nuevo ID de estudiante: ");
        String nuevoEst = scanner.nextLine();

        System.out.print("Nuevo ID de curso: ");
        String nuevoCur = scanner.nextLine();

        Inscripcion nueva = new Inscripcion();
        nueva.setIdEstudiante(nuevoEst);
        nueva.setCursoId(nuevoCur);

        boolean ok = inscripcionService.actualizarInscripcion(idEstActual, idCurActual, nueva);

        if (ok) System.out.println("Inscripción actualizada.");
        else System.out.println("No se pudo actualizar.");
    }


    // 5. ELIMINAR INSCRIPCIÓN

    private void eliminarInscripcion() {
        System.out.println("\n--- Eliminar Inscripción ---");

        System.out.print("ID del estudiante: ");
        String idEst = scanner.nextLine();

        System.out.print("ID del curso: ");
        String idCur = scanner.nextLine();

        boolean ok = inscripcionService.eliminarInscripcion(idEst, idCur);

        if (ok) System.out.println("Inscripción eliminada.");
        else System.out.println("No se pudo eliminar.");
    }


    // 6. LISTAR POR ESTUDIANTE

    private void listarPorEstudiante() {
        System.out.println("\n--- Inscripciones por estudiante ---");

        System.out.print("ID del estudiante: ");
        String idEst = scanner.nextLine();

        List<Inscripcion> lista = inscripcionService.listarPorEstudiante(idEst);

        if (lista.isEmpty()) {
            System.out.println("Este estudiante no tiene inscripciones.");
            return;
        }

        lista.forEach(ins -> System.out.println("Curso: " + ins.getCursoId()));
    }


    // 7. LISTAR POR CURSO

    private void listarPorCurso() {
        System.out.println("\n--- Inscripciones por curso ---");

        System.out.print("ID del curso: ");
        String idCur = scanner.nextLine();

        List<Inscripcion> lista = inscripcionService.listarPorCurso(idCur);

        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes inscritos en este curso.");
            return;
        }

        lista.forEach(ins -> System.out.println("Estudiante: " + ins.getIdEstudiante()));
    }

    public void menuInscripciones() {
        int opcion = 0;

        do {
            System.out.println("\n===== MENÚ INSCRIPCIONES =====");
            System.out.println("1. Registrar inscripción");
            System.out.println("2. Listar inscripciones");
            System.out.println("3. Buscar inscripción");
            System.out.println("4. Actualizar inscripción");
            System.out.println("5. Eliminar inscripción");
            System.out.println("6. Listar por estudiante");
            System.out.println("7. Listar por curso");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> registrarInscripcion();
                case 2 -> listarInscripciones();
                case 3 -> buscarInscripcion();
                case 4 -> actualizarInscripcion();
                case 5 -> eliminarInscripcion();
                case 6 -> listarPorEstudiante();
                case 7 -> listarPorCurso();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }
}

