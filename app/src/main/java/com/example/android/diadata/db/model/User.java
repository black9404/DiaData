package com.example.android.diadata.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nome")
    String nome;
    @ColumnInfo(name = "subnome")
    String subnome;
    @ColumnInfo(name = "idade")
    int idade;
    @ColumnInfo(name = "tipo_diabetes")
    boolean tipo_diabetes;
    @ColumnInfo(name = "idade_diabetes")
    int idade_diabetes;



}
