package com.example.android.diadata.db.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal")
public class Meal {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nome_meal")
    private String nome;
    @ColumnInfo(name = "id_meal")
    private int id_meal;
    @ColumnInfo(name = "id_user")
    private int id_user;
    @ColumnInfo(name = "qnt_hidratos")
    private int hidratos;
    @ColumnInfo(name = "dia_meal")
    private int dia;
    @ColumnInfo(name = "mes_meal")
    private int mes;

    public Meal(int id_meal, int id_user, int hidratos, String nome, int dia, int mes) {
        this.id_meal = id_meal;
        this.id_user = id_user;
        this.hidratos = hidratos;
        this.nome = nome;
        this.dia = dia;
        this.mes = mes;
    }

    public int getId_meal() {
        return id_meal;
    }

    public void setId_meal(int id_meal) {
        this.id_meal = id_meal;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getHidratos() {
        return hidratos;
    }

    public void setHidratos(int hidratos) {
        this.hidratos = hidratos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
}
