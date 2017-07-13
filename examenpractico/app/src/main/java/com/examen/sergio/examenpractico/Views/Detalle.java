package com.examen.sergio.examenpractico.Views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.examen.sergio.examenpractico.Models.ResponseListadoImagenes;
import com.examen.sergio.examenpractico.R;

/**
 * Created by sergio on 13/07/17.
 */

public class Detalle extends Dialog {



    public Detalle(@NonNull Context context, ResponseListadoImagenes detalle) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.detalle);

        TextView titulo =  (TextView)findViewById(R.id.titulo);
        TextView descripcion =  (TextView) findViewById(R.id.descripcion);
        ImageView imagen = (ImageView) findViewById(R.id.imagen_extendida);

        titulo.setText(detalle.getTitle());
        descripcion.setText(detalle.getDescription());
        Glide.with(context).load(detalle.getImg()).into(imagen);
    }




}
