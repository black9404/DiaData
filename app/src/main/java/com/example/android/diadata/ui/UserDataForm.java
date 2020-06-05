package com.example.android.diadata.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.android.diadata.MainActivity;
import com.example.android.diadata.R;
import com.example.android.diadata.db.model.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class UserDataForm extends Fragment {

    private TextInputEditText userNameEditText, userForenameEditText, userAgeEditText;
    private CheckBox agreementCheckBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.form_user_profile, container, false);
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

        /*Spinner genderSpinner = Objects.requireNonNull(getView()).findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> adapterGend = ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()), R.array.gender, R.layout.spinner_layout);
        adapterGend.setDropDownViewResource(R.layout.spinner_layout);
        genderSpinner.setAdapter(adapterGend);

        Spinner diabetesSpinner = Objects.requireNonNull(getView()).findViewById(R.id.diabetes_spinner);
        ArrayAdapter<CharSequence> adapterDiab = ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()), R.array.diabetes, R.layout.spinner_layout);
        adapterDiab.setDropDownViewResource(R.layout.spinner_layout);
        diabetesSpinner.setAdapter(adapterDiab);

        Button submitFormButton = Objects.requireNonNull(getView()).findViewById(R.id.button);
        submitFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToDashboard(checkIfDataIsValid());
            }
        });*/

        agreementCheckBox = Objects.requireNonNull(getView()).findViewById(R.id.checkBox);

    }

    //metodo que verifica se os dados inseridos no formulário são válidos
    private boolean checkIfDataIsValid() {

        //lista de todos os campos presentes no formulário
        List<TextInputEditText> editTextList = Arrays.asList(userNameEditText, userForenameEditText, userAgeEditText);

        //variaveis
        String nome = null, subnome = null;
        int idade = 0, tipoDiabetes = 0;

        //for loop que verifica se todos os campos foram preenchidos
        for (EditText edit : editTextList) {

            //verifica se o utilizador inseriu informação nos campos presentes no formulário
            if (TextUtils.isEmpty(edit.getText())) {
                edit.setError("Por favor, preencha o campo assinalado");
                return false;
            }

            //caso os valor inserido seja válido o mesmo é adicionada à String userInformation
            else {

                //verifica qual dos campos foi preenchido
                switch (editTextList.indexOf(edit)) {

                    //campo do nome
                    case 0:
                        nome = edit.getText().toString();
                        break;

                    //campo do subnome
                    case 1:
                        subnome = edit.getText().toString();
                        break;

                    //campo da idade
                    case 2:
                        idade = Integer.parseInt(edit.getText().toString());
                        break;

                }

            }

        }

        //verifica se o utilizador aceitou os termos
        if (!agreementCheckBox.isChecked()) {
            Toast.makeText(getContext(), "Por favor concorde com os termos para prosseguir", Toast.LENGTH_SHORT).show();
            return false;
        }

        //criado o utilizador com os dados inseridos
        User user = new User(nome, subnome, idade, tipoDiabetes, "");

        //adicionar o utilizador a base de dados
        MainActivity.diaDataDatabase.userDao().addUser(user);

        return true;
    }

    //metodo que redireciona o utilizador para o Dashboard
    private void redirectToDashboard(boolean dataIsValid) {

        //atualiza os valores das SharedPreferences para que o formulário não volte a aparecer
        if (dataIsValid) {

            //define o formulário como preenhido pelo utilizador
            formFilled();

            //redireciona o utilizador para o dashboard
            Fragment dashboard = new Dashboard();
            FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
            transaction.replace(R.id.fragment_container, dashboard);
            transaction.addToBackStack(null);
            transaction.commit();

        }

    }

    //metodo que atualiza as SharedPreferences caso o formulário se encontre preenchido
    private void formFilled() {
        SharedPreferences sharedPreferences = Objects.requireNonNull(getContext()).getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("userDataAdded", true);
        editor.apply();
    }

}
