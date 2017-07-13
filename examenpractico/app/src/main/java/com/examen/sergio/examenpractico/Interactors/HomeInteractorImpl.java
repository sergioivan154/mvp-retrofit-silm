package com.examen.sergio.examenpractico.Interactors;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.examen.sergio.examenpractico.Interfces.HomeInteractor;
import com.examen.sergio.examenpractico.Interfces.HomePresenter;
import com.examen.sergio.examenpractico.Interfces.OnListingFinishedListener;
import com.examen.sergio.examenpractico.Interfces.WsServiceExamen;
import com.examen.sergio.examenpractico.Adapters.ListadoAdapter;
import com.examen.sergio.examenpractico.Models.ResponseListadoImagenes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sergio on 13/07/17.
 */

public class HomeInteractorImpl implements HomeInteractor {

    private Retrofit retrofit;
    private WsServiceExamen servicio;
    private HomePresenter presenter;
    private Context context;

    public HomeInteractorImpl(Context context, HomePresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://canditossoft.000webhostapp.com/services/")
                .build();

        servicio = retrofit.create(WsServiceExamen.class);
    }

    @Override
    public void obtenerListado(final OnListingFinishedListener listener) {
        final Observable<List<ResponseListadoImagenes>> respuesta = servicio.getListado();
        final List<ResponseListadoImagenes> listaItems = new ArrayList<ResponseListadoImagenes>();

        respuesta.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ResponseListadoImagenes>>() {

                    ListadoAdapter adapter;
                    @Override
                    public void onCompleted() {
                        if (adapter != null) {
                            //es exitoso

                            listener.mostrarListado(adapter);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        presenter.showErrorService(e.getMessage());
                    }

                    @Override
                    public void onNext(List<ResponseListadoImagenes> responseListadoImagenes) {

                        adapter = new ListadoAdapter(context, responseListadoImagenes);
                    }


                });

    }



}
