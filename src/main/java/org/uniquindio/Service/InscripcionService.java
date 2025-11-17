package org.uniquindio.Service;


import org.uniquindio.Models.Curso;
import org.uniquindio.Models.Estudiante;
import org.uniquindio.Models.Inscripcion;
import org.uniquindio.Service.EstudianteService;

import java.util.List;
import java.util.ArrayList;

public class InscripcionService {
    private final List<Curso> cursos;
    private final List<Estudiante> estudiantes;
    private final List<Inscripcion> inscripciones;

    public InscripcionService(List<Curso> cursos, List<Estudiante> estudiantes, List<Inscripcion> inscripciones) {
        this.cursos = cursos != null ? cursos : new ArrayList<>();
        this.estudiantes = estudiantes != null ? estudiantes : new ArrayList<>();
        this.inscripciones = inscripciones != null ? new ArrayList<>(inscripciones) : new ArrayList<>();
    }

    // Validación básica de una inscripcion
    public boolean esInscripcionValida(Inscripcion inscripcion) {
        if (inscripcion == null) return false;
        String idEst = safeTrim(inscripcion.getIdEstudiante());
        String idCur = safeTrim(inscripcion.getCursoId());
        if (idEst == null || idEst.isBlank()) return false;
        if (idCur == null || idCur.isBlank()) return false;

        // comprobar existencia de estudiante y curso
        Estudiante e = buscarEstudiante(idEst);
        Curso c = buscarCurso(idCur);
        if (e == null || c == null) return false;

        // comprobar duplicado
        return buscarPorId(idEst, idCur) == null;
    }

    // CREATE
    public boolean registrarInscripcion(Inscripcion inscripcion) {
        if (!esInscripcionValida(inscripcion)) {
            System.out.println("Inscripción inválida o duplicada.");
            return false;
        }
        Curso curso = buscarCurso(inscripcion.getCursoId().trim());
        if (curso.getCupos() <= 0) {
            System.out.println("No hay cupos disponibles en el curso.");
            return false;
        }
        inscripciones.add(inscripcion);
        // reducir cupo
        curso.setCupos(curso.getCupos() - 1);
        System.out.println("Inscripción registrada correctamente: Estudiante " + inscripcion.getIdEstudiante() + " -> Curso " + inscripcion.getCursoId());
        return true;
    }

    // READ - buscar por ids (estudiante + curso)
    public Inscripcion buscarPorId(String idEstudiante, String idCurso) {
        if (idEstudiante == null || idCurso == null) return null;
        String idE = idEstudiante.trim();
        String idC = idCurso.trim();
        if (idE.isEmpty() || idC.isEmpty()) return null;

        return inscripciones.stream()
                .filter(ins -> safeTrim(ins.getIdEstudiante()) != null
                        && safeTrim(ins.getIdEstudiante()).equalsIgnoreCase(idE)
                        && safeTrim(ins.getCursoId()) != null
                        && safeTrim(ins.getCursoId()).equalsIgnoreCase(idC))
                .findFirst()
                .orElse(null);
    }

    // READ - listar todas
    public List<Inscripcion> listarInscripciones() {
        return new ArrayList<>(inscripciones);
    }

    // UPDATE - reemplaza la inscripción existente por otra (control de duplicados)
    public boolean actualizarInscripcion(String idEstudiante, String idCurso, Inscripcion datosActualizados) {
        if (idEstudiante == null || idCurso == null || datosActualizados == null) {
            System.out.println("Parámetros nulos para actualizar inscripción.");
            return false;
        }
        String idE = idEstudiante.trim();
        String idC = idCurso.trim();
        if (idE.isEmpty() || idC.isEmpty()) {
            System.out.println("IDs vacíos.");
            return false;
        }
        Inscripcion existente = buscarPorId(idE, idC);
        if (existente == null) {
            System.out.println("Inscripción no encontrada.");
            return false;
        }

        // Si los nuevos ids son distintos, comprobar que no exista duplicado
        String nuevoEst = safeTrim(datosActualizados.getIdEstudiante());
        String nuevoCur = safeTrim(datosActualizados.getCursoId());
        if (nuevoEst == null || nuevoEst.isBlank() || nuevoCur == null || nuevoCur.isBlank()) {
            System.out.println("Datos actualizados inválidos.");
            return false;
        }

        if (!idE.equalsIgnoreCase(nuevoEst) || !idC.equalsIgnoreCase(nuevoCur)) {
            // si cambia la pareja, verificar que no exista otra inscripción igual
            if (buscarPorId(nuevoEst, nuevoCur) != null) {
                System.out.println("Ya existe una inscripción con los nuevos ids.");
                return false;
            }
            // verificar existencia estudiante y curso destino
            if (buscarEstudiante(nuevoEst) == null || buscarCurso(nuevoCur) == null) {
                System.out.println("Estudiante o curso destino no existe.");
                return false;
            }
        }

        // realizar reemplazo simple
        inscripciones.remove(existente);
        inscripciones.add(datosActualizados);
        System.out.println("Inscripción actualizada correctamente.");
        return true;
    }

    // DELETE
    public boolean eliminarInscripcion(String idEstudiante, String idCurso) {
        if (idEstudiante == null || idCurso == null) {
            System.out.println("IDs nulos al eliminar.");
            return false;
        }
        String idE = idEstudiante.trim();
        String idC = idCurso.trim();
        if (idE.isEmpty() || idC.isEmpty()) {
            System.out.println("IDs vacíos al eliminar.");
            return false;
        }
        Inscripcion ins = buscarPorId(idE, idC);
        if (ins == null) {
            System.out.println("Inscripción no encontrada.");
            return false;
        }
        // eliminar e incrementar cupo del curso
        Curso curso = buscarCurso(idC);
        if (curso != null) {
            curso.setCupos(curso.getCupos() + 1);
        }
        inscripciones.remove(ins);
        System.out.println("Inscripción eliminada correctamente.");
        return true;
    }

    // Consultas auxiliares
    public List<Inscripcion> listarPorEstudiante(String idEstudiante) {
        if (idEstudiante == null) return new ArrayList<>();
        String idE = idEstudiante.trim();
        List<Inscripcion> result = new ArrayList<>();
        for (Inscripcion ins : inscripciones) {
            if (safeTrim(ins.getIdEstudiante()) != null && safeTrim(ins.getIdEstudiante()).equalsIgnoreCase(idE)) {
                result.add(ins);
            }
        }
        return result;
    }

    public List<Inscripcion> listarPorCurso(String idCurso) {
        if (idCurso == null) return new ArrayList<>();
        String idC = idCurso.trim();
        List<Inscripcion> result = new ArrayList<>();
        for (Inscripcion ins : inscripciones) {
            if (safeTrim(ins.getCursoId()) != null && safeTrim(ins.getCursoId()).equalsIgnoreCase(idC)) {
                result.add(ins);
            }
        }
        return result;
    }

    // Helpers para buscar entidades en las listas locales
    private Estudiante buscarEstudiante(String idEstudiante) {
        if (idEstudiante == null) return null;
        String id = idEstudiante.trim();
        return estudiantes.stream()
                .filter(e -> safeTrim(e.getIdEstudiante()) != null && safeTrim(e.getIdEstudiante()).equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    private Curso buscarCurso(String idCurso) {
        if (idCurso == null) return null;
        String id = idCurso.trim();
        return cursos.stream()
                .filter(c -> safeTrim(c.getIdCurso()) != null && safeTrim(c.getIdCurso()).equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    private String safeTrim(String s) {
        return s == null ? null : s.trim();
    }
}
