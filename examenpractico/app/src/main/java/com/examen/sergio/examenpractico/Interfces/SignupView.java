package com.examen.sergio.examenpractico.Interfces;

/**
 * Created by sergio on 13/07/17.
 */

public interface SignupView {
    void showProgress();
    void hideProgress();

    void setErrorUser();
    void setErrorPassword();
    void setErrorMail();

    void navidateToHome(String preferencia, String sesion);

    void showErrorService(String error);
}
