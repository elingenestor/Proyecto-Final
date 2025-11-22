package org.uniquindio.Views;

// NOMBRE DE LOS INTEGRANTES: NESTOR ALEJANDRO ROJAS GONZALEZ

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
import org.uniquindio.Controllers.InscripcionController;
import org.uniquindio.Models.Calificacion;
import org.uniquindio.Models.TipoCalificacion;
import org.uniquindio.Controllers.CalificacionController;
import org.uniquindio.Service.CalificacionService;
import org.uniquindio.Service.InscripcionService;


public class MenuConsola {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EstudianteService service = new EstudianteService();
        EstundianteController controller = new EstundianteController(service);

        ProfesorService profesorService = new ProfesorService();
        ProfesorController profesorController = new ProfesorController(profesorService);

        CursoService cursoService = new CursoService();
        CursoController cursoController = new CursoController(cursoService);

        InscripcionService inscripcionService = new InscripcionService(cursoService.listarCursos(), service.listarEstudiantes(), null);
        InscripcionController inscripcionController = new InscripcionController(cursoService.listarCursos(), service.listarEstudiantes(), inscripcionService.listarInscripciones());

        CalificacionService CalificacionService = new CalificacionService(service.listarEstudiantes(), cursoService.listarCursos());
        CalificacionController CalificacionController = new CalificacionController(CalificacionService);

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

            System.out.println("16. Gestión de inscripciones");

            System.out.println("17. Registrar calificación");
            System.out.println("18. Listar calificaciones");
            System.out.println("19. Buscar calificación específica");
            System.out.println("20. Actualizar calificación");
            System.out.println("21. Eliminar calificación");

            System.out.println(". Salir");


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
                    inscripcionController.menuInscripciones();
                    break;

                case 17:
                    System.out.print("Valor: ");
                    double valor = Double.parseDouble(scanner.nextLine());

                    System.out.print("ID estudiante: ");
                    String idEstCal = scanner.nextLine();

                    System.out.print("ID curso: ");
                    String idCursoCal = scanner.nextLine();

                    System.out.print("Porcentaje: ");
                    double porcentaje = Double.parseDouble(scanner.nextLine());

                    System.out.print("Fecha (AAAA-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(scanner.nextLine());

                    System.out.print("Observaciones: ");
                    String observaciones = scanner.nextLine();

                    System.out.print("Tipo (EXAMEN, TAREA, QUIZ, PROYECTO): ");
                    TipoCalificacion tipo = TipoCalificacion.valueOf(scanner.nextLine().toUpperCase());

                    boolean okCreada = CalificacionController.crearCalificacion(
                            valor, idEstCal, idCursoCal, porcentaje, fecha, observaciones, tipo
                    );
                    System.out.println(okCreada ? "Calificación registrada." : "Error al registrar.");
                    break;

                case 18:
                    List<Calificacion> listaC = CalificacionController.obtenerTodas();
                    if (listaC.isEmpty()) System.out.println("No hay calificaciones registradas.");
                    else listaC.forEach(System.out::println);
                    break;

                case 19:
                    System.out.print("ID estudiante: ");
                    String idEstB = scanner.nextLine();

                    System.out.print("ID curso: ");
                    String idCurB = scanner.nextLine();

                    System.out.print("Fecha (AAAA-MM-DD): ");
                    LocalDate fechaB = LocalDate.parse(scanner.nextLine());

                    Calificacion encontradaCal = CalificacionController.buscarCalificacionEspecifica(idEstB, idCurB, fechaB);

                    System.out.println(encontradaCal == null ? "No encontrada." : encontradaCal);
                    break;

                case 20:
                    System.out.print("ID estudiante: ");
                    String idEstUpd = scanner.nextLine();

                    System.out.print("ID curso: ");
                    String idCurUpd = scanner.nextLine();

                    System.out.print("Fecha original (AAAA-MM-DD): ");
                    LocalDate fechaOrig = LocalDate.parse(scanner.nextLine());

                    System.out.print("Nuevo valor: ");
                    double nuevoVal = Double.parseDouble(scanner.nextLine());

                    System.out.print("Nuevo porcentaje: ");
                    double nuevoPorc = Double.parseDouble(scanner.nextLine());

                    System.out.print("Nueva fecha (AAAA-MM-DD): ");
                    LocalDate nuevaFecha = LocalDate.parse(scanner.nextLine());

                    System.out.print("Nuevo tipo: ");
                    TipoCalificacion tipoNuevo = TipoCalificacion.valueOf(scanner.nextLine().toUpperCase());

                    System.out.print("Observaciones: ");
                    String obsNuevas = scanner.nextLine();

                    boolean okAct = CalificacionController.actualizarCalificacion(
                            idEstUpd, idCurUpd, fechaOrig, nuevoVal, nuevoPorc, nuevaFecha, tipoNuevo, obsNuevas
                    );
                    System.out.println(okAct ? "Calificación actualizada." : "No se pudo actualizar.");
                    break;

                case 21:
                    System.out.print("ID estudiante: ");
                    String idEstDel = scanner.nextLine();

                    System.out.print("ID curso: ");
                    String idCurDel = scanner.nextLine();

                    System.out.print("Fecha (AAAA-MM-DD): ");
                    LocalDate fechaDel = LocalDate.parse(scanner.nextLine());

                    boolean okDel = CalificacionController.eliminarCalificacion(idEstDel, idCurDel, fechaDel);
                    System.out.println(okDel ? "Calificación eliminada." : "No se pudo eliminar.");
                    break;

                case 22:
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
