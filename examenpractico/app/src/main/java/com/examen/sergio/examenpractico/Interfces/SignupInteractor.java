package com.examen.sergio.examenpractico.Interfces;

/**
 * Created by sergio on 13/07/17.
 */

public interface SignupInteractor {
    void registrarUsuario(String user, String password,String mail, OnSignUpFinishedListener listener);
}
