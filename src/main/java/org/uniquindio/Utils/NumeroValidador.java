package org.uniquindio.Utils;

public class NumeroValidador {

    public static boolean mayorQueCero(int numero){
        return numero > 0;
    }

    public static boolean enRango(double valor, double min, double max){
        return valor >= min && valor <= max;
    }
}
