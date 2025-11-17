package org.uniquindio.Service;

import org.uniquindio.Models.Curso;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.uniquindio.Models.Profesor;

public class CursoService {


    private final List<Curso> cursos = new ArrayList<>();

    //METODO PARA DETERMINAR SI EL CURSO ES VALIDO
    public boolean esCursoValido(Curso curso) {
        if (curso == null)
            return false;

        String id = curso.getIdCurso() == null ? null : curso.getIdCurso().trim();
        String nombre = curso.getNombreCurso() == null ? null : curso.getNombreCurso().trim();

        if (id == null || id.isBlank())
            return false;
        if (nombre == null || nombre.isBlank())
            return false;
        if (curso.getCupos() <= 0)
            return false;

        List<Profesor> profesores = curso.getProfesorAsignados();
        if (profesores != null) {
            long distinctNonNull = profesores.stream().filter(Objects::nonNull).distinct().count();
            if (profesores.size() != distinctNonNull)
                return false;
        }

        // ID UNICO (usar id trimmed)
        if (buscarPorId(id) != null)
            return false;

        return true;
    }

    // CREATE - METODO PARA REGISTRAR CURSO
    public boolean registrarCurso(Curso curso) {
        if (esCursoValido(curso)) {
            cursos.add(curso);
            System.out.println("Curso agregado correctamente: " + curso.getNombreCurso());
            return true;
        }
        System.out.println("No se pudo registrar el curso, Datos invalidos o nulos.");
        return false;
    }

    // READ - BUSCAR CURSO POR ID
    public Curso buscarPorId(String idCurso) {
        if (idCurso == null)
            return null;
        String id = idCurso.trim();
        if (id.isEmpty())
            return null;

        return cursos.stream()
                .filter(curso -> curso.getIdCurso() != null && curso.getIdCurso().trim().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    //READ - LISTAR TODOS LOS CURSOS
    public List<Curso> listarCursos() {
        return new ArrayList<>(cursos); //Se retorna una copia para evitar modificaciones externas
    }

    // UPDATE - ACTUALIZAR DATOS DE UN CURSO
    public boolean actualizarCurso(String idCurso, Curso datosActualizados) {
        if (idCurso == null) {
            System.out.println("ID de curso nulo.");
            return false;
        }
        if (datosActualizados == null) {
            System.out.println("Datos para actualizar nulos.");
            return false;
        }

        String id = idCurso.trim();
        if (id.isEmpty()) {
            System.out.println("ID de curso vacío.");
            return false;
        }

        Curso existente = buscarPorId(id);
        if (existente == null) {
            System.out.println("Curso con ID: " + id + " No encontrado.");
            return false;
        }
        if (datosActualizados.getNombreCurso() != null && !datosActualizados.getNombreCurso().isBlank())
            existente.setNombreCurso(datosActualizados.getNombreCurso().trim());
        if (datosActualizados.getCupos() > 0)
            existente.setCupos(datosActualizados.getCupos());

        System.out.println("Curso actualizado correctamente: " + existente.getNombreCurso());
        return true;
    }

    //DELETE - METODO PARA ELIMINAR CURSO
    public boolean eliminarCurso(String idCurso) {
        if (idCurso == null) {
            System.out.println("ID de curso nulo.");
            return false;
        }
        String id = idCurso.trim();
        if (id.isEmpty()) {
            System.out.println("ID de curso vacío.");
            return false;
        }

        Curso curso = buscarPorId(id);
        if (curso != null) {
            cursos.remove(curso);
            System.out.println("Curso eliminado correctamente: " + curso.getNombreCurso());
            return true;
        }
        System.out.println("No se encontro el curso con ID: " + id);
        return false;
    }
}
