package com.examen.sergio.examenpractico.Interactors;


import com.examen.sergio.examenpractico.Interfces.OnSignUpFinishedListener;
import com.examen.sergio.examenpractico.Interfces.SignupInteractor;
import com.examen.sergio.examenpractico.Interfces.SignupPresenter;
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
 * Created by sergio on 13/07/17.
 */

public class SignUpInteractorImpl implements SignupInteractor {

    private Retrofit retrofit;
    private WsServiceExamen servicio;
    private SignupPresenter presenter;

    public SignUpInteractorImpl(SignupPresenter presenter) {
        this.presenter = presenter;
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://canditossoft.000webhostapp.com/services/")
                .build();

        servicio = retrofit.create(WsServiceExamen.class);

    }

    @Override
    public void registrarUsuario(final String user, final String password, String mail, final OnSignUpFinishedListener listener) {
        if (!user.equals("") && !password.equals("") & !mail.equals("")) {

            final String[] errorServicio = {""};
            final Observable<ResponseValidaUsuario> respuesta = servicio.setNuevoUsuario(user, password, mail);

            respuesta.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ResponseValidaUsuario>() {
                        @Override
                        public void onCompleted() {
                            if (errorServicio[0] == "") {
                                //es exitoso
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
                            //cuando se esta regsitrando un usuario que ya existe no regresa un objeto si no un arreglo []
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
            if (mail.equals("")) {
                listener.mailError();
            }
        }
    }
}
