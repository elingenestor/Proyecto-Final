package org.uniquindio.Utils;

import java.time.LocalDate;

public class ValidadorFecha {

    //METODO PARA VALIDAR QUE LA FECHA NO ES FUTURA
    public static boolean fechaNoFutura (LocalDate fecha){
        return fecha != null && !fecha.isAfter(LocalDate.now());
    }

    //METODO PARA ESTABLECER UN RANGO LOGICO
    public static boolean fechaEnRango (LocalDate fecha, LocalDate fechaInicio, LocalDate fechaFin){
        return fecha != null && (fecha.isEqual(fechaInicio) || fecha.isAfter(fechaInicio)) &&
                (fecha.isEqual(fechaFin) || fecha.isBefore(fechaFin));
    }
}
