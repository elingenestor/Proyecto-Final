package org.uniquindio.Models;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nombreCurso;
    private String idCurso;
    private List<Profesor> profesorAsignados;
    private int cupos;

    //METODO CONSTRUCTOR

    public Curso(String nombreCurso, String idCurso, List<Profesor> profesorAsignados, int cupos) {
        this.nombreCurso = nombreCurso;
        this.idCurso = idCurso;
        this.profesorAsignados = new ArrayList<Profesor>();
        this.cupos = cupos;
    }

    //METODOS GETTER Y SETTER


    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public List<Profesor> getProfesorAsignados() {
        return profesorAsignados;
    }

    public void setProfesorAsignados(List<Profesor> profesorAsignados) {
        this.profesorAsignados = profesorAsignados;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    //METODO TOSTRING
    @Override
    public String toString() {
        return "Curso: " +
                "\n Nombre del Curso: " + nombreCurso +
                "\n ID del Curso: " + idCurso +
                "\n Porfesores asignados al curso: " + (profesorAsignados.isEmpty() ? "Ninguno" : profesorAsignados.size()) +
                "\n";
    }
}
