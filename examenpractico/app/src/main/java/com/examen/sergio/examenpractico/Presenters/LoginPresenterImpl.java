package com.examen.sergio.examenpractico.Presenters;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;

import com.examen.sergio.examenpractico.Interactors.LoginInteractorImpl;
import com.examen.sergio.examenpractico.Interfces.LoginInteractor;
import com.examen.sergio.examenpractico.Interfces.LoginPresenter;
import com.examen.sergio.examenpractico.Interfces.LoginView;
import com.examen.sergio.examenpractico.Interfces.OnLoginFinishedListener;

/**
 * Created by sergio on 12/07/17.
 */

public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener{
    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void validarUsuario(String user, String password) {
        if(view != null) {
            view.showProgress();
            interactor.validarUsuario(user, password, this);
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
    public void exitoOperacion(String nombrePreferencia, String sesion) {
        if(view != null) {
            view.hideProgress();
            view.navidateToHome(nombrePreferencia, sesion);
        }
    }
}
