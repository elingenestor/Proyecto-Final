package org.uniquindio.Utils;

public class Validador {

    //METODO PARA VALIDAR QUE UNA CADENA DE TEXTO NO ESTE VACIA O NULL
    public static boolean textoValido(String texto){
        return texto != null && !texto.trim().isEmpty();
    }

    //METODO PARA VALIDAR CANITDAD ESTUDIANTES PRO CURSO
    public static boolean cantidadEstudiantesValida (int cantidad, int maximo){
        return cantidad > 0 && cantidad <= maximo;
    }

    //METODO PARA VALIDAR UN EMAIL/CORREO ELECTRONICO
    public static boolean emailValido(String email){
        return email != null && !email.matches("[A-Za-z0-9+_.-]+ @(.+)$");
    }
}
