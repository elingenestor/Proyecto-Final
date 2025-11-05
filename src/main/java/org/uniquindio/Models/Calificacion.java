package org.uniquindio.Models;

import java.time.LocalDate;

public class Calificacion {

    private double valor;
    private String idEstudiante;
    private String idCurso;
    private double porcentaje;
    private LocalDate fecha;
    private String observaciones;
    private TipoCalificacion tipoCalificacion;

    //METODO CONSTRUCTOR
    public Calificacion(double valor, String idEstudiante, String idCurso) {
        this.valor = valor;
        this.idEstudiante = idEstudiante;
        this.idCurso = idCurso;
    }

    //METODOS GETTER Y SETTER
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoCalificacion getTipoCalificacion() {
        return tipoCalificacion;
    }

    public void setTipoCalificacion(TipoCalificacion tipoCalificacion) {
        this.tipoCalificacion = tipoCalificacion;
    }
}
