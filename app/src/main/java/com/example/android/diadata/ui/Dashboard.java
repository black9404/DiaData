package com.example.android.diadata.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.diadata.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class Dashboard extends Fragment {

    private TextView userNameTextView;
    private FloatingActionButton addFloatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        fetchData();
    }

    //metodo que instancia todos os elementos presentes no layout
    private void initViews() {
        userNameTextView = Objects.requireNonNull(getView()).findViewById(R.id.username);
        addFloatingActionButton = getView().findViewById(R.id.fab);
        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
    }

    //metodo que preenche as informações armazenadas
    private void fetchData() {

        //insere o nome do utilizador
        //userNameTextView.setText(String.format(getResources().getString(R.string.welcome_sentence_user), ));


    }

    //metodo que adiciona um alimento/refeição
    private void addData() {

    }

}
