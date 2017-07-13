package com.examen.sergio.examenpractico.Interfces;

/**
 * Created by sergio on 12/07/17.
 */

public interface LoginView {

    void showProgress();
    void hideProgress();

    void setErrorUser();
    void setErrorPassword();

    void navidateToHome(String preferencia, String sesion );

    void showErrorService(String error);
}
