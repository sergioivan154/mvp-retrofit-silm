package com.examen.sergio.examenpractico.Interfces;

/**
 * Created by sergio on 13/07/17.
 */

public interface OnSignUpFinishedListener {
    void userNameError();
    void passwordError();
    void mailError();
    void exitoOperacion(String preferencia, String sesion);
}
