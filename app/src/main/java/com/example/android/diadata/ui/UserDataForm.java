package com.example.android.diadata.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class UserDataForm extends Fragment {

    private EditText userNameEditText, userForenameEditText, userAgeEditText;
    private Spinner genderSpinner, diabetesSpinner;

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

        genderSpinner = Objects.requireNonNull(getView()).findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> adapterGend = ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()), R.array.gender, android.R.layout.simple_spinner_dropdown_item);
        adapterGend.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapterGend);

        diabetesSpinner = Objects.requireNonNull(getView()).findViewById(R.id.diabetes_spinner);
        ArrayAdapter<CharSequence> adapterDiab = ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()), R.array.diabetes, android.R.layout.simple_spinner_dropdown_item);
        adapterDiab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diabetesSpinner.setAdapter(adapterDiab);

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

        //variaveis
        String nome = null, subnome = null, genero;
        int idade = 0, tipoDiabetes;

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

        //verifica se o utilizador selecionou um gênero
        if (genderSpinner != null) {
            genero = genderSpinner.getSelectedItem().toString();
        } else {
            Toast.makeText(getContext(),"Por favor, selecione um gênero",Toast.LENGTH_SHORT).show();
            return false;
        }

        //verifica se o utilizador selecionou o tipo de diabetes
        if (diabetesSpinner != null) {
            tipoDiabetes = Integer.parseInt(diabetesSpinner.getSelectedItem().toString());
        } else {
            Toast.makeText(getContext(),"Por favor, selecione o tipo de diabetes que possui",Toast.LENGTH_SHORT).show();
            return false;
        }

        //criado o utilizador com os dados inseridos
        User user = new User(nome, subnome, idade, tipoDiabetes, genero);

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
