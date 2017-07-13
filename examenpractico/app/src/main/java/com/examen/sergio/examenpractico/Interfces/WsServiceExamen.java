package com.examen.sergio.examenpractico.Interfces;


import com.examen.sergio.examenpractico.Models.ResponseListadoImagenes;
import com.examen.sergio.examenpractico.Models.ResponseValidaUsuario;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sergio on 13/07/17.
 */

public interface WsServiceExamen  {

    @GET("searchuser.php?user_name&user_pass")
    Observable<ResponseValidaUsuario> getDataValidacionUsuario(@Query("user_name") String userName, @Query("user_pass") String password);

    @POST("insertuser.php?username&userpass&mail")
    Observable<ResponseValidaUsuario> setNuevoUsuario(@Query("username") String userName, @Query("userpass") String password, @Query("mail") String mail);

    @GET("searchfeed.php")
    Observable<List<ResponseListadoImagenes>> getListado();



}
