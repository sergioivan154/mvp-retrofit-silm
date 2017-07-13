package com.examen.sergio.examenpractico.Presenters;

import android.app.Fragment;
import android.content.Context;

import com.examen.sergio.examenpractico.Interactors.HomeInteractorImpl;
import com.examen.sergio.examenpractico.Interfces.HomeInteractor;
import com.examen.sergio.examenpractico.Interfces.HomePresenter;
import com.examen.sergio.examenpractico.Interfces.HomeView;
import com.examen.sergio.examenpractico.Interfces.OnListingFinishedListener;
import com.examen.sergio.examenpractico.Adapters.ListadoAdapter;

/**
 * Created by sergio on 13/07/17.
 */

public class HomePresenterImpl implements HomePresenter, OnListingFinishedListener {

    private HomeView view;
    private HomeInteractor interactor;

    public HomePresenterImpl(HomeView view, Context context) {
        this.view = view;
        interactor = new HomeInteractorImpl(context, this);
    }

    @Override
    public void mostrarListado(ListadoAdapter adapter) {
        if (view != null) {
            view.hideProgress();
            view.showListing(adapter);
        }
    }

    @Override
    public void showErrorService(String error) {
        if (view != null) {
            view.hideProgress();
            view.showErrorService(error);
        }
    }

    @Override
    public void obtenerListado() {
        if(view != null) {
            view.showProgress();
            interactor.obtenerListado(this);
        }
    }


}
