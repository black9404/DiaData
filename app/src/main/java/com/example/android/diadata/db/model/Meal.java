package com.example.android.diadata.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal")
public class Meal {

    @PrimaryKey(autoGenerate = true)
    public int id_meal;
    @ColumnInfo(name = "id_user")
    public int id_user;
    @ColumnInfo(name = "qnt_hidratos")
    public int hidratos;
    @ColumnInfo(name = "nome_meal")
    public String nome;
    @ColumnInfo(name = "dia_meal")
    public int dia;
    @ColumnInfo(name = "mes_meal")
    public int mes;

    public Meal(int id_meal, int id_user, int hidratos, String nome, int dia, int mes) {
        this.id_meal = id_meal;
        this.id_user = id_user;
        this.hidratos = hidratos;
        this.nome = nome;
        this.dia = dia;
        this.mes = mes;
    }
}
