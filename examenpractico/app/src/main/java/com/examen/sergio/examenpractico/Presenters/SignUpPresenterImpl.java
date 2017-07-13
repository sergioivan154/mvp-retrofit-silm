package com.examen.sergio.examenpractico.Presenters;

import com.examen.sergio.examenpractico.Interactors.SignUpInteractorImpl;
import com.examen.sergio.examenpractico.Interfces.OnSignUpFinishedListener;
import com.examen.sergio.examenpractico.Interfces.SignupInteractor;
import com.examen.sergio.examenpractico.Interfces.SignupPresenter;
import com.examen.sergio.examenpractico.Interfces.SignupView;

/**
 * Created by sergio on 13/07/17.
 */

public class SignUpPresenterImpl implements SignupPresenter, OnSignUpFinishedListener {

    private SignupView view;
    private SignupInteractor interactor;

    public SignUpPresenterImpl(SignupView view) {
        this.view = view;
        interactor = new SignUpInteractorImpl(this);
    }

    @Override
    public void registrarUsuario(String user, String password, String mail) {
        if(view != null) {
            view.showProgress();
            interactor.registrarUsuario(user, password, mail, this);
        }
    }

    @Override
    public void showErrorService(String error) {
        if(view != null) {
            view.hideProgress();
            view.showErrorService(error);
        }
    }


    @Override
    public void userNameError() {
        if(view != null) {
            view.hideProgress();
            view.setErrorUser();
        }
    }

    @Override
    public void passwordError() {
        if(view != null) {
            view.hideProgress();
            view.setErrorPassword();
        }
    }

    @Override
    public void mailError() {
        if(view != null) {
            view.hideProgress();
            view.setErrorMail();
        }
    }

    @Override
    public void exitoOperacion(String preferencia, String sesion) {
        if(view != null) {
            view.hideProgress();
            view.navidateToHome(preferencia, sesion);
        }
    }
}
