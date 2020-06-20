package com.example.android.diadata.db.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal")
public class Meal {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_tabela_meal")
    private int id_tabela_meal;
    @ColumnInfo(name = "nome_meal")
    private String nome;
    @ColumnInfo(name = "id_meal")
    private int id_meal;
    @ColumnInfo(name = "id_alimento")
    private int id_alimento;
    @ColumnInfo(name = "qnt_hidratos")
    private double hidratos;
    @ColumnInfo(name = "dia_meal")
    private int dia;
    @ColumnInfo(name = "mes_meal")
    private int mes;

    public Meal(int id_meal, int id_alimento, double hidratos, String nome, int dia, int mes) {
        this.id_meal = id_meal;
        this.id_alimento = id_alimento;
        this.hidratos = hidratos;
        this.nome = nome;
        this.dia = dia;
        this.mes = mes;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public int getId_tabela_meal() {
        return id_tabela_meal;
    }

    public void setId_tabela_meal(int id_tabela_meal) {
        this.id_tabela_meal = id_tabela_meal;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    public int getId_meal() {
        return id_meal;
    }

    public void setId_meal(int id_meal) {
        this.id_meal = id_meal;
    }

    public int getId_alimento() {
        return id_alimento;
    }

    public void setId_alimento(int id_alimento) {
        this.id_alimento = id_alimento;
    }

    public double getHidratos() {
        return hidratos;
    }

    public void setHidratos(int hidratos) {
        this.hidratos = hidratos;
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
