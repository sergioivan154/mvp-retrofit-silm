package com.examen.sergio.examenpractico.Interactors;

import android.content.ClipData;
import android.os.Handler;
import android.util.Base64;

import com.examen.sergio.examenpractico.Interfces.LoginInteractor;
import com.examen.sergio.examenpractico.Interfces.LoginPresenter;
import com.examen.sergio.examenpractico.Interfces.OnLoginFinishedListener;
import com.examen.sergio.examenpractico.Interfces.WsServiceExamen;
import com.examen.sergio.examenpractico.Models.ResponseValidaUsuario;
import com.examen.sergio.examenpractico.Utils.Security;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by sergio on 12/07/17.
 */

public class LoginInteractorImpl implements LoginInteractor  {

    private Retrofit retrofit;
    private WsServiceExamen servicio;
    private LoginPresenter presenter;


    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://canditossoft.000webhostapp.com/services/")
                .build();

        servicio = retrofit.create(WsServiceExamen.class);

    }

    @Override
    public void validarUsuario(final String user, final String password, final OnLoginFinishedListener listener) {
        if (!user.equals("") && !password.equals("")) {

            final String[] errorServicio = {""};
            final Observable<ResponseValidaUsuario> respuesta = servicio.getDataValidacionUsuario(user, password);

            respuesta.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ResponseValidaUsuario>() {
                        @Override
                        public void onCompleted() {
                            if (errorServicio[0] == "") {
                                //es exitoso y tenemos que guardar la preferencia encriptada
                                String nombrePreferencia = Security.encrypt("sesion");
                                String sesion = Security.encrypt(user+"|"+password);
                                listener.exitoOperacion(nombrePreferencia, sesion);
                            }
                            else{
                                presenter.showErrorService(errorServicio[0]);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            presenter.showErrorService(e.getMessage());
                        }

                        @Override
                        public void onNext(ResponseValidaUsuario responseValidaUsuario) {
                            errorServicio[0] = responseValidaUsuario.getError().getErrMsg();

                        }
                    });



        } else {
            if (user.equals("")) {
                listener.userNameError();
            }
            if (password.equals("")) {
                listener.passwordError();
            }
        }

    }



}
