package com.example.android.diadata.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.diadata.R;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class Dashboard extends Fragment {

    private TextView userNameTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    //metodo que instancia todos os elementos presentes no layout
    private void initViews() {
        userNameTextView = Objects.requireNonNull(getView()).findViewById(R.id.username);
    }

    //metodo que preenche as informações armazenadas
    private void fetchData() {

        //inicializado as SharedPreferences de forma a obter a informação armazenada
        SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("prefs", MODE_PRIVATE);

        //isolado o nome do utilizador da String que esta armazenada nas SharedPreferences


        //insere o nome do utilizador
        userNameTextView.setText(String.format(getResources().getString(R.string.welcome_sentence_user), sharedPreferences.getString()));;


    }

}
