package com.example.android.diadata.core;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class DataProcessing {

    private static String name, forename;
    private static int age;

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

    public static void setName(String name) {
        DataProcessing.name = name;
    }

    public static String getForename() {
        return forename;
    }

    public static void setForename(String forename) {
        DataProcessing.forename = forename;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        DataProcessing.age = age;
    }
}
