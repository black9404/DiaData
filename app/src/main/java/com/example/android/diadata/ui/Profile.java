package com.example.android.diadata.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.diadata.MainActivity;
import com.example.android.diadata.R;

import java.util.Objects;

public class Profile extends Fragment {

    private TextView userNameTextView, ageTextView, typeDiabetesTextView, genderTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        fetchDataFromDb();
    }

    //metodo que instancia todos os elementos presentes no layout
    private void initViews() {

        ImageView backButtonImageView = Objects.requireNonNull(getView()).findViewById(R.id.backButton);
        backButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStackImmediate();
            }
        });

        userNameTextView = Objects.requireNonNull(getView()).findViewById(R.id.userName);

        ageTextView = Objects.requireNonNull(getView()).findViewById(R.id.idadeForm);

        typeDiabetesTextView = Objects.requireNonNull(getView()).findViewById(R.id.tipoDiabetesForm);

        genderTextView = Objects.requireNonNull(getView()).findViewById(R.id.generoForm);
    }

    //metodo que preenche os campos com a informação do utilizador
    private void fetchDataFromDb() {
        //obtem o nome e o subnome do utilizador
        String nomeP = MainActivity.diaDataDatabase.userDao().getUserName();
        String nomeS = MainActivity.diaDataDatabase.userDao().getUserSubname();
        //junta os nomes obtidos
        String nome = nomeP + " " + nomeS;

        //obtem a idade do utilizador
        String idade = String.valueOf(MainActivity.diaDataDatabase.userDao().getIdade());

        //obtem o tipo de diabetes do utilizador
        String TDiabetes;
        if (MainActivity.diaDataDatabase.userDao().getTDiabetes() == 1){
            TDiabetes = "Tipo 1";
        }else{
            TDiabetes = "Tipo 2";
        }

        //Obtem o genero do utilizador
        String genero = MainActivity.diaDataDatabase.userDao().getGenero();

        //insere os dados no layout
        userNameTextView.setText(nome);
        ageTextView.setText(idade);
        typeDiabetesTextView.setText(TDiabetes);
        genderTextView.setText(genero);
    }

}