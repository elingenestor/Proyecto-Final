package org.uniquindio.Models;

import java.time.LocalDate;

public class Inscripcion {

    private String idInscripcion;
    private String idEstudiante;
    private String cursoId;
    private LocalDate fechaInscripcion;
    private String periodo;
    private EstadoInscripcion estadoInscripcion;
    private Modalidad modalidad;
    private LocalDate fechaCancelacion;

    //METODO CONSTRUCTOR
    public Inscripcion (String idInscripcion, String idEstudiante, String cursoId, LocalDate fechaInscripcion,
                        String periodo, EstadoInscripcion estadoInscripcion, Modalidad modalidad, LocalDate fechaCancelacion) {
        this.idInscripcion = idInscripcion;
        this.idEstudiante = idEstudiante;
        this.cursoId = cursoId;
        this.fechaInscripcion = fechaInscripcion;
        this.periodo = periodo;
        this.estadoInscripcion = estadoInscripcion;
        this.modalidad = modalidad;
        this.fechaCancelacion = fechaCancelacion;
    }

    //METODOS GETTER Y SETTER

    public String getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(String idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getCursoId() {
        return cursoId;
    }

    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public EstadoInscripcion getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(EstadoInscripcion estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public LocalDate getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDate fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }
}
