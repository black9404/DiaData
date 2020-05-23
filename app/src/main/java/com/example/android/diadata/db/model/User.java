package com.example.android.diadata.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "nome")
    public String nome;
    @ColumnInfo(name = "subnome")
    public String subnome;
    @ColumnInfo(name = "idade")
    public int idade;
    @ColumnInfo(name = "tipo_diabetes")
    public boolean tipo_diabetes;
    @ColumnInfo(name = "genero")
    public String genero;

    public User(int id, String nome, String subnome, int idade, boolean tipo_diabetes, String genero) {
        this.id = id;
        this.nome = nome;
        this.subnome = subnome;
        this.idade = idade;
        this.tipo_diabetes = tipo_diabetes;
        this.genero = genero;
    }

    }

