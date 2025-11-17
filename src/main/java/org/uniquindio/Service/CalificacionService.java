package org.uniquindio.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.uniquindio.Models.Calificacion;
import org.uniquindio.Models.Curso;
import org.uniquindio.Models.Estudiante;

public class CalificacionService {
    private final List<Calificacion> calificaciones;
    private final List<Estudiante> estudiantes;
    private final List<Curso> cursos;

    public CalificacionService(List<Estudiante> estudiantes, List<Curso> cursos) {
        this.calificaciones = new ArrayList<>();
        this.estudiantes = estudiantes != null ? estudiantes : new ArrayList<>();
        this.cursos = cursos != null ? cursos : new ArrayList<>();
    }

    // ---------------------------------------------------------
    // VALIDACIÓN
    // ---------------------------------------------------------
    public boolean esCalificacionValida(Calificacion c) {
        if (c == null) return false;
        if (c.getValor() < 0 || c.getValor() > 5) return false;
        if (c.getIdEstudiante() == null || c.getIdEstudiante().isBlank()) return false;
        if (c.getIdCurso() == null || c.getIdCurso().isBlank()) return false;

        // El estudiante debe existir
        Estudiante est = buscarEstudiante(c.getIdEstudiante());
        if (est == null) return false;

        // El curso debe existir
        Curso cur = buscarCurso(c.getIdCurso());
        if (cur == null) return false;

        return true;
    }

    // ---------------------------------------------------------
    // CREATE - Registrar calificación
    // ---------------------------------------------------------
    public boolean registrarCalificacion(Calificacion c) {
        if (!esCalificacionValida(c)) {
            System.out.println("Calificación inválida.");
            return false;
        }

        // Fecha por defecto
        if (c.getFecha() == null)
            c.setFecha(LocalDate.now());

        calificaciones.add(c);
        System.out.println("Calificación registrada correctamente.");
        return true;
    }

    // ---------------------------------------------------------
    // READ - Buscar calificación por estudiante y curso
    // ---------------------------------------------------------
    public List<Calificacion> buscarPorEstudianteCurso(String idEst, String idCurso) {
        List<Calificacion> result = new ArrayList<>();

        for (Calificacion c : calificaciones) {
            if (c.getIdEstudiante().equalsIgnoreCase(idEst) &&
                    c.getIdCurso().equalsIgnoreCase(idCurso)) {
                result.add(c);
            }
        }

        return result;
    }

    // Buscar una calificación específica (por fecha)
    public Calificacion buscarEspecifica(String idEst, String idCurso, LocalDate fecha) {
        for (Calificacion c : calificaciones) {
            if (c.getIdEstudiante().equalsIgnoreCase(idEst) &&
                    c.getIdCurso().equalsIgnoreCase(idCurso) &&
                    c.getFecha().equals(fecha)) {
                return c;
            }
        }
        return null;
    }

    // ---------------------------------------------------------
    // READ - Listar todas
    // ---------------------------------------------------------
    public List<Calificacion> listarCalificaciones() {
        return new ArrayList<>(calificaciones);
    }

    // ---------------------------------------------------------
    // UPDATE - Modificar calificación
    // ---------------------------------------------------------
    public boolean actualizarCalificacion(String idEst, String idCurso, LocalDate fechaOriginal, Calificacion nueva) {

        Calificacion existente = buscarEspecifica(idEst, idCurso, fechaOriginal);

        if (existente == null) {
            System.out.println("La calificación no existe.");
            return false;
        }

        // Validar lo nuevo
        if (!esCalificacionValida(nueva)) {
            System.out.println("Nuevos datos inválidos.");
            return false;
        }

        // Actualizar datos
        existente.setValor(nueva.getValor());
        existente.setPorcentaje(nueva.getPorcentaje());
        existente.setTipoCalificacion(nueva.getTipoCalificacion());
        existente.setObservaciones(nueva.getObservaciones());
        existente.setFecha(nueva.getFecha() != null ? nueva.getFecha() : existente.getFecha());

        System.out.println("Calificación actualizada correctamente.");
        return true;
    }

    // ---------------------------------------------------------
    // DELETE - Eliminar calificación
    // ---------------------------------------------------------
    public boolean eliminarCalificacion(String idEst, String idCurso, LocalDate fecha) {
        Calificacion c = buscarEspecifica(idEst, idCurso, fecha);

        if (c == null) {
            System.out.println("No se encontró la calificación a eliminar.");
            return false;
        }

        calificaciones.remove(c);
        System.out.println("Calificación eliminada correctamente.");
        return true;
    }

    // ---------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------
    private Estudiante buscarEstudiante(String id) {
        return estudiantes.stream()
                .filter(e -> e.getIdEstudiante().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    private Curso buscarCurso(String id) {
        return cursos.stream()
                .filter(c -> c.getIdCurso().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }
}

