package com.example.android.diadata.db.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dashdata")
public class DashData {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_info")
    private int id_info;
    @ColumnInfo(name = "dia_info")
    private int dia;
    @ColumnInfo(name = "mes_info")
    private int mes;
    @ColumnInfo(name = "ano_info")
    private int ano;
    @ColumnInfo(name = "hora_info")
    private int hora;
    @ColumnInfo(name = "minuto_info")
    private int minutos;
    @ColumnInfo(name = "id_meal")
    private int id_meal;
    @ColumnInfo(name = "hidratos")
    private double hidratos;
    @ColumnInfo(name = "dose")
    private int dose;

    public DashData(int dia, int mes, int ano, int hora, int minutos, int id_meal, double hidratos, int dose) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.minutos = minutos;
        this.id_meal = id_meal;
        this.hidratos = hidratos;
        this.dose = dose;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getId_info() {
        return id_info;
    }

    public void setId_info(int id_info) {
        this.id_info = id_info;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getId_meal() {
        return id_meal;
    }

    public void setId_meal(int id_meal) {
        this.id_meal = id_meal;
    }

    public double getHidratos() {
        return hidratos;
    }

    public void setHidratos(int hidratos) {
        this.hidratos = hidratos;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }
}
