package com.example.android.diadata.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.android.diadata.R;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class UserDataForm extends Fragment {

    private EditText userNameEditText, userForenameEditText, userAgeEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_data_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    //metodo que instancia todos os elementos presentes no layout
    private void initViews() {
        userNameEditText = Objects.requireNonNull(getView()).findViewById(R.id.userNameForm);
        userForenameEditText = Objects.requireNonNull(getView()).findViewById(R.id.userForenameForm);
        userAgeEditText = Objects.requireNonNull(getView()).findViewById(R.id.userAgeForm);

        Button submitFormButton = Objects.requireNonNull(getView()).findViewById(R.id.button);
        submitFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToDashboard(checkIfDataIsValid());
            }
        });
    }

    //metodo que verifica se os dados inseridos no formulário são válidos
    private boolean checkIfDataIsValid() {

        //lista de todos os campos presentes no formulário
        List<EditText> editTextList = Arrays.asList(userNameEditText, userForenameEditText, userAgeEditText);

        //for loop que verifica se todos os campos foram preenchidos
        for (EditText edit : editTextList) {

            //verifica se o utilizador inseriu informação nos campos presentes no formulário
            if (TextUtils.isEmpty(edit.getText())) {
                edit.setError("Por favor, preencha o campo assinalado");
                return false;
            }

            //caso os valor inserido seja válido o mesmo é adicionada à String userInformation
            else {
                String userInformation = edit.getText().toString();
                UserInformation.storeUserData(userInformation);
            }

        }

        //se os valores inseridos forem válidos
        return true;
    }

    //metodo que atualiza as SharedPreferences caso os dados inseridos sejam válidos
    private void redirectToDashboard(boolean dataIsValid) {

        //atualiza os valores das SharedPreferences para que o formulário não volte a aparecer
        if (dataIsValid) {

            //redireciona o utilizador para o dashboard
            Fragment dashboard = new Dashboard();
            FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
            transaction.replace(R.id.fragment_container, dashboard);
            transaction.addToBackStack(null);
            transaction.commit();

        }

    }

}
