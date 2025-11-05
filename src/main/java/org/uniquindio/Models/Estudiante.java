package org.uniquindio.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String idEstudiante;
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private String numeroTelefono;
    private LocalDate fechaNacimiento;
    private String carrera;
    private int semestreActual;
    private List<Curso> cursosInscritos;



    //METODO CONSTRUCTOR
    public Estudiante(String idEstudiante, String nombres, String apellidos, String correoElectronico,
                      String numeroTelefono, LocalDate fechaNacimiento, String carrera, int semestreActual) {
        this.idEstudiante = idEstudiante;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.fechaNacimiento = fechaNacimiento;
        this.carrera = carrera;
        this.semestreActual = semestreActual;
        this.cursosInscritos = new ArrayList<>();
    }

    //GETTERS Y SETTERS


    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
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

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getSemestreActual() {
        return semestreActual;
    }

    public void setSemestreActual(int semestreActual) {
        this.semestreActual = semestreActual;
    }

    public List<Curso> getCursosInscritos() {
        return cursosInscritos;
    }

    public void setCursosInscritos(List<Curso> cursosInscritos) {
        this.cursosInscritos = cursosInscritos;
    }
}
