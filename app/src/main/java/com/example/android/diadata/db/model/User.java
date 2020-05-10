package com.example.android.diadata.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nome")
    private String nome;
    @ColumnInfo(name = "subnome")
    private String subnome;
    @ColumnInfo(name = "idade")
    private int idade;
    @ColumnInfo(name = "tipo_diabetes")
    private boolean tipo_diabetes;
    @ColumnInfo(name = "idade_diabetes")
    private int idade_diabetes;

    public User(int id, String nome, String subnome, int idade, boolean tipo_diabetes, int idade_diabetes) {
        this.id = id;
        this.nome = nome;
        this.subnome = subnome;
        this.idade = idade;
        this.tipo_diabetes = tipo_diabetes;
        this.idade_diabetes = idade_diabetes;
    }

    @Ignore
    public User(String nome, String subnome, int idade, boolean tipo_diabetes, int idade_diabetes) {
        this.nome = nome;
        this.subnome = subnome;
        this.idade = idade;
        this.tipo_diabetes = tipo_diabetes;
        this.idade_diabetes = idade_diabetes;
    }

}
