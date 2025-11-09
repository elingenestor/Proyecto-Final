package org.uniquindio.Views;

import java.time.LocalDate;
import java.util.Scanner;
import org.uniquindio.Service.EstudianteService;
import org.uniquindio.Controllers.EstundianteController;


public class MenuConsola {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       EstudianteService service = new EstudianteService();
       EstundianteController controller = new EstundianteController(service);

       int option;
       do {
           System.out.println("\n===== MENÚ PRINCIPAL =====");
           System.out.println("1. Registrar estudiante");
           System.out.println("2. Listar estudiantes");
           System.out.println("3. Buscar estudiante");
           System.out.println("4. Eliminar estudiante");
           System.out.println("5. Salir");
           System.out.print("Selecciona una opción: ");

           option = Integer.parseInt(scanner.nextLine());

           switch (option) {
               case 1:
                   System.out.print("Nombres: ");
                   String nombres = scanner.nextLine();
                   System.out.print("Apellidos: ");
                   String apellidos = scanner.nextLine();
                   System.out.print("ID estudiante: ");
                   String idEstudiante = scanner.nextLine();
                   System.out.print("Correo Electronico: ");
                   String correoElectronico = scanner.nextLine();
                   System.out.print("Numero Telefono: ");
                   String numeroTelefono = scanner.nextLine();
                   System.out.print("Fecha de Nacimiento (AAAA-MM-DD): ");
                   LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine());
                   System.out.print("Carrera: ");
                   String carrera = scanner.nextLine();
                   System.out.print("SemestreActual: ");
                   int semestreActual = Integer.parseInt(scanner.nextLine());
                   System.out.print("Fecha de Ingreso (AAAA-MM-DD): ");
                   LocalDate fechaIngreso = LocalDate.parse(scanner.nextLine());


                   controller.registrarEstudiante(nombres,apellidos,idEstudiante, correoElectronico, numeroTelefono,fechaNacimiento,carrera,semestreActual,fechaIngreso);
                   break;

               case 2:
                   controller.listarEstudiantes();
                   break;

               case 3:
                   System.out.print("ID a buscar: ");
                   String idBuscar = scanner.nextLine();
                   controller.buscarEstudiante(idBuscar);
                   break;

               case 4:
                   System.out.print("Código a eliminar: ");
                   String codigoEliminar = scanner.nextLine();
                   controller.eliminarEstudiante(codigoEliminar);
                   break;

               case 5:
                   System.out.println("👋 Saliendo del sistema...");
                   break;

               default:
                   System.out.println("⚠️ Opción no válida.");
           }
       } while (option != 5);

        scanner.close();
    }
}

