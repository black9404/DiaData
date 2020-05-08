package com.example.android.diadata.ui;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class UserInformation {

    private static String name, forename;
    private int age;

    static void storeUserData(ArrayList<String> userInformation, Context context) {

        //arraylist organizada para obter os valores inseridos pelo utilizador
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString("userData", userInformation);
        editor.apply();

    }

    static void formFilled(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("userDataAdded", true);
        editor.apply();
    }

    static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
