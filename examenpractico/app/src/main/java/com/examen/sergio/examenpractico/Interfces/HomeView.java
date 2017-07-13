package com.examen.sergio.examenpractico.Interfces;

import com.examen.sergio.examenpractico.Adapters.ListadoAdapter;

/**
 * Created by sergio on 13/07/17.
 */

public interface HomeView {

    void showErrorService(String error);
    void showListing(ListadoAdapter adapter);
    void hideProgress();
    void showProgress();
}
