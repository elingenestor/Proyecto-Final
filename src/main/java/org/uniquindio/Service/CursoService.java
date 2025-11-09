package org.uniquindio.Service;

import org.uniquindio.Models.Curso;
import java.util.ArrayList;
import java.util.List;
import org.uniquindio.Models.Profesor;

public class CursoService {

    private List<Curso> cursos = new ArrayList<>();

    //METODO PARA DETERMINAR SI EL CURSO ES VALIDO
    public boolean esCursoValido(Curso curso) {
        if (curso == null)
            return false;
        if (curso.getIdCurso() == null || curso.getIdCurso().isBlank())
            return false;
        if (curso.getNombreCurso() == null || curso.getNombreCurso().isBlank())
            return false;
        if (curso.getCupos() <= 0)
            return false;

        List<Profesor> profesores = curso.getProfesorAsignados();
        if (profesores != null && profesores.size() != profesores.stream().distinct().count())
            return false;

        //ID UNICO
        if (buscarPorId(curso.getIdCurso()) != null)
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
        return cursos.stream()
                .filter(curso -> curso.getIdCurso().equalsIgnoreCase(idCurso))
                .findFirst()
                .orElse(null);
    }

    //READ - LISTAR TODOS LOS CURSOS
    public List<Curso> listarCursos() {
        return new ArrayList<>(cursos); //Se retorna una copia para evitar modificaciones externas
    }

    // UPDATE - ACTUALIZAR DATOS DE UN CURSO
    public boolean actualizarCurso(String idCurso, Curso datosActualizados) {
        Curso existente = buscarPorId(idCurso);
        if (existente == null) {
            System.out.println("Curso con ID: " + idCurso + " No encontrado.");
            return false;
        }
        if (datosActualizados.getNombreCurso() != null && !datosActualizados.getNombreCurso().isBlank())
            existente.setNombreCurso(datosActualizados.getNombreCurso());
        if (datosActualizados.getCupos() > 0)
            existente.setCupos(datosActualizados.getCupos());

        System.out.println("Curso actualizado correctamente: " + existente.getNombreCurso());
        return true;
    }

    //DELETE - METODO PARA ELIMINAR CURSO
    public boolean eliminarCurso(String idCurso) {
        Curso curso = buscarPorId(idCurso);
        if (curso == null) {
            cursos.remove(curso);
            System.out.println("Curso eliminado correctamente: " + curso.getNombreCurso());
            return true;
        }
        System.out.println("No se encontro el curso con ID: " + idCurso);
        return false;
    }
}
