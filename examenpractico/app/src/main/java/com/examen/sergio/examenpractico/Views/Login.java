package com.examen.sergio.examenpractico.Views;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.examen.sergio.examenpractico.Interfces.LoginPresenter;
import com.examen.sergio.examenpractico.Interfces.LoginView;
import com.examen.sergio.examenpractico.Presenters.LoginPresenterImpl;
import com.examen.sergio.examenpractico.R;

import static android.content.Context.MODE_PRIVATE;

public class Login extends Fragment implements LoginView, View.OnClickListener {

    private EditText user, password;
    private ProgressBar progressBar;
    private LoginPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login, container, false);

        user = (EditText) view.findViewById(R.id.editTextUserName);
        password = (EditText) view.findViewById(R.id.editTextPassword);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        presenter = new LoginPresenterImpl(this);

        Button btnLogin = (Button) view.findViewById(R.id.buttonValidar);
        btnLogin.setOnClickListener(this);
        Button btnRegistro = (Button) view.findViewById(R.id.buttonRegistrarse);
        btnRegistro.setOnClickListener(this);
        return view;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorUser() {
        user.setError("Campo obligatorio");
    }

    @Override
    public void setErrorPassword() {
        password.setError("Campo obligatorio");
    }

    @Override
    public void navidateToHome(String preferencia, String sesion) {


        SharedPreferences preferences = (getActivity().getSharedPreferences("privado", MODE_PRIVATE));
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(preferencia, sesion);
        editor.apply();

        //para aparecer el boton de log out desde el activity principal
        ((Main) getActivity()).logoutBtn.setVisibility(View.VISIBLE);

        //cerrar teclado
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        //cargar el nuevo fragment

        Home home = new Home();


        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.mainFragment, home);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void showErrorService(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.buttonValidar) {
            presenter.validarUsuario(user.getText().toString(), password.getText().toString());
        }
        else if (v.getId() == R.id.buttonRegistrarse){
            Signup registro = new Signup();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFragment, registro);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
