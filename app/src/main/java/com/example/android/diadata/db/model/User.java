package com.example.android.diadata.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
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
    private int tipo_diabetes;
    @ColumnInfo(name = "genero")
    private String genero;

    public User(String nome, String subnome, int idade, int tipo_diabetes, String genero) {
        this.nome = nome;
        this.subnome = subnome;
        this.idade = idade;
        this.tipo_diabetes = tipo_diabetes;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSubnome() {
        return subnome;
    }

    public void setSubnome(String subnome) {
        this.subnome = subnome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getTipo_diabetes() {
        return tipo_diabetes;
    }

    public void setTipo_diabetes(int tipo_diabetes) {
        this.tipo_diabetes = tipo_diabetes;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}

