package com.examen.sergio.examenpractico.Views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.examen.sergio.examenpractico.Interfces.SignupPresenter;
import com.examen.sergio.examenpractico.Interfces.SignupView;
import com.examen.sergio.examenpractico.Presenters.LoginPresenterImpl;
import com.examen.sergio.examenpractico.Presenters.SignUpPresenterImpl;
import com.examen.sergio.examenpractico.R;

import static android.content.Context.MODE_PRIVATE;

public class Signup extends Fragment implements SignupView, View.OnClickListener {

    private EditText user, password, mail;
    private ProgressBar progressBar;
    private SignupPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.signup, container, false);

        user = (EditText) view.findViewById(R.id.editTextUserName);
        password = (EditText) view.findViewById(R.id.editTextPassword);
        mail = (EditText) view.findViewById(R.id.editTextMail);


        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);


        presenter = new SignUpPresenterImpl(this);
        Button btn = (Button) view.findViewById(R.id.buttonRegistrarse);
        btn.setOnClickListener(this);

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
    public void setErrorMail() {
        mail.setError("Campo obligatorio");
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
        presenter.registrarUsuario(user.getText().toString(), password.getText().toString(), mail.getText().toString());
    }
}
