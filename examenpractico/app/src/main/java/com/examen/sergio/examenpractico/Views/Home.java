package com.examen.sergio.examenpractico.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.examen.sergio.examenpractico.Interfces.HomePresenter;
import com.examen.sergio.examenpractico.Interfces.HomeView;
import com.examen.sergio.examenpractico.Adapters.ListadoAdapter;
import com.examen.sergio.examenpractico.Models.ResponseListadoImagenes;
import com.examen.sergio.examenpractico.Presenters.HomePresenterImpl;
import com.examen.sergio.examenpractico.R;

/**
 * Created by sergio on 13/07/17.
 */

public class Home extends Fragment implements HomeView, AdapterView.OnItemClickListener {

    private HomePresenter presenter;
    private GridView listado;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home, container, false);
        listado = (GridView) view.findViewById(R.id.grid);
        listado.setOnItemClickListener(this);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        presenter = new HomePresenterImpl(this, getContext());
        presenter.obtenerListado();

        return view;

    }

    @Override
    public void showErrorService(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showListing(ListadoAdapter adapter) {
        listado.setAdapter(adapter);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ResponseListadoImagenes item = (ResponseListadoImagenes) parent.getItemAtPosition(position);

        Detalle detalleScreen = new Detalle(this.getContext(), item);

        detalleScreen.show();

    }
}
