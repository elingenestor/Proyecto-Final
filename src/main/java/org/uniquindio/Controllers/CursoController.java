package org.uniquindio.Controllers;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import org.uniquindio.Service.CursoService;
import org.uniquindio.Models.Curso;
import org.uniquindio.Models.Profesor;


public class CursoController {
    private final CursoService service;
    private final Scanner scanner = new Scanner(System.in);

    public CursoController(CursoService cursoService) {
        this.service = cursoService;
    }

    // Crea y registra un curso usando el servicio
    public boolean registrarCurso(String idCurso, String nombreCurso, int cupos) {
        List<Profesor> profesoresAsignados = new ArrayList<>();
        Curso curso = new Curso(idCurso, nombreCurso, profesoresAsignados, cupos);
        return service.registrarCurso(curso);
    }

    // Retorna un curso por id
    public Curso buscarCurso(String idCurso) {
        return service.buscarPorId(idCurso);
    }

    // Retorna lista de cursos
    public List<Curso> listarCursos() {
        return service.listarCursos();
    }

    // Actualiza nombre y/o cupos de un curso
    public boolean actualizarCurso(String idCurso, String nuevoNombre, int nuevosCupos) {
        Curso datos = new Curso(idCurso, nuevoNombre, new ArrayList<>(), nuevosCupos);
        return service.actualizarCurso(idCurso, datos);
    }

    // Elimina un curso por id
    public boolean eliminarCurso(String idCurso) {
        return service.eliminarCurso(idCurso);
    }

}

