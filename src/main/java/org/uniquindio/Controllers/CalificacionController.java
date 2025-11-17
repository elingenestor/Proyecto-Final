package org.uniquindio.Controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.uniquindio.Models.Calificacion;
import org.uniquindio.Models.TipoCalificacion;
import org.uniquindio.Service.CalificacionService;

public class CalificacionController {

    private final CalificacionService calificacionService;

        public CalificacionController(CalificacionService calificacionService) {
            this.calificacionService = calificacionService;
        }

        // CREATE

        public boolean crearCalificacion(double valor, String idEstudiante,  String idCurso,  double porcentaje,  LocalDate fecha,  String observaciones,  TipoCalificacion tipoCalificacion) {

            Calificacion c = new Calificacion(valor, idEstudiante, idCurso);
            c.setPorcentaje(porcentaje);
            c.setFecha(fecha);
            c.setObservaciones(observaciones);
            c.setTipoCalificacion(tipoCalificacion);

            return calificacionService.registrarCalificacion(c);
        }


        // READ

        public List<Calificacion> buscarCalificaciones(String idEstudiante, String idCurso) {
            return calificacionService.buscarPorEstudianteCurso(idEstudiante, idCurso);
        }

        public List<Calificacion> obtenerTodas() {
            return calificacionService.listarCalificaciones();
        }

        public Calificacion buscarCalificacionEspecifica(String idEstudiante, String idCurso, LocalDate fecha) {
            return calificacionService.buscarEspecifica(idEstudiante, idCurso, fecha);
        }


        // UPDATE

        public boolean actualizarCalificacion(String idEstudiante,
                                              String idCurso,
                                              LocalDate fechaOriginal,
                                              double nuevoValor,
                                              double nuevoPorcentaje,
                                              LocalDate nuevaFecha,
                                              TipoCalificacion tipo,
                                              String observaciones) {

            Calificacion nueva = new Calificacion(nuevoValor, idEstudiante, idCurso);
            nueva.setPorcentaje(nuevoPorcentaje);
            nueva.setFecha(nuevaFecha);
            nueva.setTipoCalificacion(tipo);
            nueva.setObservaciones(observaciones);

            return calificacionService.actualizarCalificacion(idEstudiante, idCurso, fechaOriginal, nueva);
        }


        // DELETE

        public boolean eliminarCalificacion(String idEstudiante, String idCurso, LocalDate fecha) {
           return calificacionService.eliminarCalificacion(idEstudiante, idCurso, fecha);
        }
}

