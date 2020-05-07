package com.example.android.diadata.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.diadata.R;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class UserDataForm extends Fragment {

    private EditText userNameEditText, userForenameEditText, userAgeEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_data_form, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        updatePreferences(checkIfDataIsValid());
    }

    //metodo que instancia todos os elementos presentes no layout
    private void initViews() {

        userNameEditText = Objects.requireNonNull(getView()).findViewById(R.id.userName);
        userForenameEditText = Objects.requireNonNull(getView()).findViewById(R.id.userForename);
        userAgeEditText = Objects.requireNonNull(getView()).findViewById(R.id.userAge);

    }

    //metodo que verifica se os dados inseridos no formulário são válidos
    private boolean checkIfDataIsValid() {

        //lista de todos os campos presentes no formulário
        List<EditText> editTextList = Arrays.asList(userNameEditText, userForenameEditText, userAgeEditText);

        //for loop que verifica se todos os campos foram preenchidos
        for (EditText edit : editTextList) {

            if (TextUtils.isEmpty(edit.getText())) {
                edit.setError("Por favor, preencha o campo assinalado");
                return false;
            }

            //verifica se os campos foram preenchidos corretamente
            /*else if () {
            return false;
            }*/
        }

        //se os valores inseridos forem válidos
        return false;
    }

    //metodo que atualiza as SharedPreferences caso os dados inseridos sejam válidos
    private void updatePreferences(boolean dataIsValid) {

        //atualiza os valores das SharedPreferences para que o formulário não volte a aparecer
        if (dataIsValid) {
            SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit().putBoolean("userDataAdded", true);
            editor.apply();
        }

    }

}
