package com.examen.sergio.examenpractico.Views;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.examen.sergio.examenpractico.R;
import com.examen.sergio.examenpractico.Utils.Security;

/**
 * Created by sergio on 13/07/17.
 */

public class Main extends AppCompatActivity{

    ImageButton logoutBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        logoutBtn = (ImageButton)findViewById (R.id.logoutBtn);
        // manda al fragment por default

        String sesion = "";

        SharedPreferences preferences = getSharedPreferences("privado", MODE_PRIVATE);
        sesion = preferences.getString(Security.encrypt("sesion"),"");
        sesion = Security.decrypt(sesion);
        android.support.v4.app.Fragment fragment = null;
        if(sesion.equals("")) {
            fragment = new Login();
            logoutBtn.setVisibility(View.GONE);
        }else {
            fragment = new Home();
            logoutBtn.setVisibility(View.VISIBLE);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, fragment).commit();
    }

    /**
     * Para salir de la sesion actual
     */
    public void salir(View v){
        logoutBtn.setVisibility(View.GONE);
        SharedPreferences preferences = getSharedPreferences("privado", MODE_PRIVATE);


        preferences.edit().remove(Security.encrypt("sesion")).commit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, new Login()).commit();
    }

}
