package org.uniquindio.Views;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.uniquindio.Controllers.CursoController;
import org.uniquindio.Controllers.ProfesorController;
import org.uniquindio.Models.Curso;
import org.uniquindio.Service.CursoService;
import org.uniquindio.Service.EstudianteService;
import org.uniquindio.Controllers.EstundianteController;
import org.uniquindio.Service.ProfesorService;


public class MenuConsola {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EstudianteService service = new EstudianteService();
        EstundianteController controller = new EstundianteController(service);

        ProfesorService profesorService = new ProfesorService();
        ProfesorController profesorController = new ProfesorController(profesorService);

        CursoService cursoService = new CursoService();
        CursoController cursoController = new CursoController(cursoService);

        int option = 0;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Buscar estudiante");
            System.out.println("4. Actualizar estudiante");
            System.out.println("5. Eliminar estudiante");
            System.out.println("6. Registrar profesor");
            System.out.println("7. Listar profesores");
            System.out.println("8. Buscar profesor");
            System.out.println("9. Actualizar profesor");
            System.out.println("10. Eliminar profesor");
            System.out.println("11. Registrar curso");
            System.out.println("12. Listar cursos");
            System.out.println("13. Buscar curso");
            System.out.println("14. Actualizar curso");
            System.out.println("15. Eliminar curso");
            System.out.println("16. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                option = -1;
            }

            switch (option) {
                case 1:
                    System.out.print("Nombres: ");
                    String nombres = scanner.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidos = scanner.nextLine();
                    System.out.print("ID estudiante: ");
                    String idEstudiante = scanner.nextLine().trim();
                    System.out.print("Correo Electronico: ");
                    String correoElectronico = scanner.nextLine();
                    System.out.print("Numero Telefono: ");
                    String numeroTelefono = scanner.nextLine();
                    System.out.print("Fecha de Nacimiento (AAAA-MM-DD): ");
                    LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine().trim());
                    System.out.print("Carrera: ");
                    String carrera = scanner.nextLine();
                    System.out.print("SemestreActual: ");
                    int semestreActual = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Fecha de Ingreso (AAAA-MM-DD): ");
                    LocalDate fechaIngreso = LocalDate.parse(scanner.nextLine().trim());

                    controller.registrarEstudiante(idEstudiante, nombres, apellidos, correoElectronico, numeroTelefono, fechaNacimiento, carrera, semestreActual, fechaIngreso);
                    break;

                case 2:
                    controller.listarEstudiantes();
                    break;

                case 3:
                    System.out.print("ID a buscar: ");
                    String idBuscar = scanner.nextLine().trim();
                    controller.buscarEstudiante(idBuscar);
                    break;

                case 4:
                    System.out.print("ID estudiante a actualizar: ");
                    String idActualizar = scanner.nextLine().trim();
                    controller.actualizarEstudiante(idActualizar);
                    break;

                case 5:
                    System.out.print("ID a eliminar: ");
                    String codigoEliminar = scanner.nextLine().trim();
                    controller.eliminarEstudiante(codigoEliminar);
                    break;


                case 6:
                    System.out.print("ID profesor: ");
                    String idProfesor = scanner.nextLine().trim();
                    System.out.print("Nombres: ");
                    String profNombres = scanner.nextLine();
                    System.out.print("Apellidos: ");
                    String profApellidos = scanner.nextLine();
                    System.out.print("Correo Electronico: ");
                    String profCorreo = scanner.nextLine();
                    System.out.print("Materia: ");
                    String materia = scanner.nextLine();

                    profesorController.registrarProfesor(idProfesor, profNombres, profApellidos, profCorreo, materia);
                    break;

                case 7:
                    profesorController.listarProfesores();
                    break;

                case 8:
                    System.out.print("ID profesor a buscar: ");
                    String idBuscarProf = scanner.nextLine().trim();
                    profesorController.buscarProfesor(idBuscarProf);
                    break;

                case 9:
                    System.out.print("ID profesor a actualizar: ");
                    String idActualizarProf = scanner.nextLine().trim();
                    profesorController.actualizarProfesor(idActualizarProf);
                    break;

                case 10:
                    System.out.print("ID profesor a eliminar: ");
                    String idEliminarProf = scanner.nextLine().trim();
                    profesorController.eliminarProfesor(idEliminarProf);
                    break;

                case 11:
                    System.out.print("ID curso: ");
                    String idCurso = scanner.nextLine().trim();
                    System.out.print("Nombre curso: ");
                    String nombreCurso = scanner.nextLine().trim();
                    System.out.print("Cupos: ");
                    int cuposRegistrar = parseIntSafe(scanner.nextLine().trim(), -1);
                    if (cuposRegistrar <= 0) {
                        System.out.println("Valor de cupos inválido. Debe ser mayor que 0.");
                        break;
                    }
                    boolean registrado = cursoController.registrarCurso(idCurso, nombreCurso, cuposRegistrar);
                    System.out.println(registrado ? "Curso registrado." : "No se pudo registrar el curso.");
                    break;

                case 12:
                    List<Curso> cursos = cursoController.listarCursos();
                    if (cursos.isEmpty()) {
                        System.out.println("No hay cursos registrados.");
                    } else {
                        cursos.forEach(c -> System.out.println(c.getIdCurso() + " - " + c.getNombreCurso() + " (cupos: " + c.getCupos() + ")"));
                    }
                    break;

                case 13:
                    System.out.print("ID curso a buscar: ");
                    String idBuscarCurso = scanner.nextLine().trim();
                    Curso encontrado = cursoController.buscarCurso(idBuscarCurso);
                    System.out.println(encontrado == null ? "Curso no encontrado." : "Curso encontrado: " + encontrado.getNombreCurso());
                    break;

                case 14:
                    System.out.print("ID curso a actualizar: ");
                    String idActualizarCurso = scanner.nextLine().trim();
                    System.out.print("Nuevo nombre (dejar vacío para no cambiar): ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Nuevos cupos (0 o negativo para no cambiar): ");
                    int nuevosCupos = parseIntSafe(scanner.nextLine().trim(), 0);
                    boolean actualizado = cursoController.actualizarCurso(idActualizarCurso, nuevoNombre, nuevosCupos);
                    System.out.println(actualizado ? "Curso actualizado." : "No se pudo actualizar el curso.");
                    break;

                case 15:
                    System.out.print("ID curso a eliminar: ");
                    String idEliminarCurso = scanner.nextLine().trim();
                    boolean eliminado = cursoController.eliminarCurso(idEliminarCurso);
                    System.out.println(eliminado ? "Curso eliminado." : "No se pudo eliminar el curso.");
                    break;

                case 16:
                    System.out.println("👋 Saliendo del sistema...");
                    break;

                default:
                    System.out.println("⚠️ Opción no válida.");
            }
        } while (option != 16);

        scanner.close();
    }

    private static int parseIntSafe(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
