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
import com.example.android.diadata.core.DataProcessing;

import java.util.Objects;

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
        fetchData();
    }

    //metodo que instancia todos os elementos presentes no layout
    private void initViews() {
        userNameTextView = Objects.requireNonNull(getView()).findViewById(R.id.username);
    }

    //metodo que preenche as informações armazenadas
    private void fetchData() {

        //insere o nome do utilizador
        userNameTextView.setText(String.format(getResources().getString(R.string.welcome_sentence_user), DataProcessing.getName()));


    }

}
