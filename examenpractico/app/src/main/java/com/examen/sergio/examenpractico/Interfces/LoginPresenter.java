package com.examen.sergio.examenpractico.Interfces;

/**
 * Created by sergio on 12/07/17.
 */

public interface LoginPresenter {
    void validarUsuario(String user, String password);
    void showErrorService(String error);
}
