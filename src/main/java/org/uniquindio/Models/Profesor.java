package org.uniquindio.Models;

import java.util.ArrayList;
import java.util.List;

public class Profesor {

    private String idProfesor;
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private List<Curso> cursosAsignados;

    //METODO CONTRUCTOR

    public Profesor(String idProfesor, String nombres, String apellidos, String correoElectronico,  List<Curso> cursosAsignados) {
        this.idProfesor = idProfesor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.cursosAsignados = new ArrayList<Curso>();
    }

    //METODOS GETTER Y SETTER


    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<Curso> getCursosAsignados() {
        return cursosAsignados;
    }

    public void setCursosAsignados(List<Curso> cursosAsignados) {
        this.cursosAsignados = cursosAsignados;
    }
}
