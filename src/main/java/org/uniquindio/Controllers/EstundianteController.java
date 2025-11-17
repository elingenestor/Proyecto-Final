package org.uniquindio.Controllers;

import org.uniquindio.Models.Estudiante;
import org.uniquindio.Service.EstudianteService;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;

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
        List<Estudiante> listaEstudiantes = service.listarEstudiantes();
        Estudiante encontrado = service.buscarEstudiante(idEstudiante);
        if (encontrado != null){
            System.out.println("Estudiante encontrado: " +  encontrado);
        } else {
            System.out.println("No se encontro el estudiante con el id: " + idEstudiante);
        }
    }

    public void actualizarEstudiante(String idEstudiante){
        Scanner scanner = new Scanner(System.in);

        String id = idEstudiante;
        if (id == null || id.isBlank()) {
            System.out.print("Ingrese el ID del estudiante a actualizar: ");
            id = scanner.nextLine();
        }

        System.out.println("Dejar en blanco (o ingresar 0 en semestre) para no cambiar ese campo.");

        System.out.print("Nuevo nombre (o Enter para no cambiar): ");
        String nombre = scanner.nextLine();

        System.out.print("Nuevo apellido (o Enter para no cambiar): ");
        String apellido = scanner.nextLine();

        System.out.print("Nuevo correo (o Enter para no cambiar): ");
        String correo = scanner.nextLine();

        System.out.print("Nuevo número telefónico (o Enter para no cambiar): ");
        String telefono = scanner.nextLine();

        System.out.print("Nueva carrera (o Enter para no cambiar): ");
        String carrera = scanner.nextLine();

        System.out.print("Nuevo semestre (o 0 para no cambiar): ");
        int semestre = 0;
        try {
            String semInput = scanner.nextLine();
            if (!semInput.isBlank()) {
                semestre = Integer.parseInt(semInput);
            }
        } catch (NumberFormatException e) {
            semestre = 0;
        }

        // Construir Estudiante con los valores capturados; usar null/0 para los campos no cambiados
        Estudiante datosActualizados = new Estudiante(
                id,
                nombre.isBlank() ? null : nombre,
                apellido.isBlank() ? null : apellido,
                correo.isBlank() ? null : correo,
                telefono.isBlank() ? null : telefono,
                null, // fechaNacimiento no se actualiza aquí
                carrera.isBlank() ? null : carrera,
                semestre, // 0 significa "no cambiar" según convención del servicio
                null // fechaIngreso no se actualiza aquí
        );

        // Llamar al método del Service como instancia
        boolean actualizado = service.actualizarEstudiante(id, datosActualizados);

        if (actualizado) {
            System.out.println("✔ Estudiante actualizado correctamente.");
        } else {
            System.out.println("❌ No se pudo actualizar el estudiante.");
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
