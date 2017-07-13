package com.examen.sergio.examenpractico.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.examen.sergio.examenpractico.Models.ResponseListadoImagenes;
import com.examen.sergio.examenpractico.R;

import java.util.List;

/**
 * Created by sergio on 13/07/17.
 */


public class ListadoAdapter extends BaseAdapter {


    List<ResponseListadoImagenes> listado;
    Context context;
    public ListadoAdapter(Context context, List<ResponseListadoImagenes> listado) {
        this.listado = listado;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listado.size();
    }

    @Override
    public ResponseListadoImagenes getItem(int position) {
        return listado.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_home, viewGroup, false);
        }



        ImageView imagen = (ImageView) view.findViewById(R.id.imagenCargada);

        //Esta linea decide si ocupa uno o dos lugares en el gridview
        GridView.LayoutParams glp = (GridView.LayoutParams) view.getLayoutParams();
        int iDisplayWidth = context.getResources().getDisplayMetrics().widthPixels ;
        if (position == listado.size()-1) {
            int iImageWidth = (iDisplayWidth);
            glp.width = iImageWidth;
            view.setLayoutParams(glp);
        }

        String urlImg = listado.get(position).getImg();
        Glide.with(view).load(urlImg).into(imagen);
        return view;
    }
}

