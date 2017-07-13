package com.examen.sergio.examenpractico.Interfces;

/**
 * Created by sergio on 13/07/17.
 */

public interface OnLoginFinishedListener {
    void userNameError();
    void passwordError();
    //trae los datos encrptados
    void exitoOperacion(String nombrePreferencia, String sesion);
}
