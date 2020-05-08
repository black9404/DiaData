package com.example.android.diadata.ui;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class UserInformation {

    private String name, forename;
    private int age;

    static void storeUserData(String userInformation) {

        //gson instanciado para colocar a informação do utilizador num em JSON
        Gson gson = new Gson();

        SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userData", userInformation);
        editor.putBoolean("userDataAdded", true);
        editor.apply();

    }

    public void loadUserData() {



    }

}
