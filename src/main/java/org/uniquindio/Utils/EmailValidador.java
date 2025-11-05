package org.uniquindio.Utils;

public class EmailValidador {
        public static boolean esEmailValido(String email){
            return email != null && !email.isBlank() && email.matches("[A-Za-z0-9+_.-]+@(.+)$");

        }
}
