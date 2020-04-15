package com.example.android.diadata.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.android.diadata.R;

import static android.content.Context.MODE_PRIVATE;

public class UserDataForm extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_data_form, container, false);

        //instanciados todos os elementos presentes no formulário


        checkIfDataIsValid();

        return view;
    }

    //metodo é chamado para verificar se os dados inseridos são válidos
    public boolean checkIfDataIsValid() {

        //se os valores inseridos forem válidos


            //atualiza os valores das SharedPreferences para que o formulário não volte a aparecer
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit().putBoolean("userDataAdded", true);
            editor.apply();


        return false;
    }

}
