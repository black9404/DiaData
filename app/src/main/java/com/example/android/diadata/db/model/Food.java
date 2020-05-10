package com.example.android.diadata.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "food")
public class Food {

    @PrimaryKey(autoGenerate = true)
    int id_alimento;
    @ColumnInfo(name = "nome")
    String nome;
    @ColumnInfo(name = "hidratos")
    int hidratos;
    @ColumnInfo(name = "tipo_alimento")
    boolean tipo_alimento;
    @ColumnInfo(name = "quantidade_alimento")
    int quantidade_alimeto;

    public Food(int id, String nome, int hidratos, boolean t_alimento, int qnt_alimento) {
        this.id_alimento = id;
        this.nome = nome;
        this.hidratos = hidratos;
        this.tipo_alimento = t_alimento;
        this.quantidade_alimeto = qnt_alimento;
    }

    @Ignore
    public Food(String nome, int hidratos, boolean t_alimento, int qnt_alimento) {
        this.nome = nome;
        this.hidratos = hidratos;
        this.tipo_alimento = t_alimento;
        this.quantidade_alimeto = qnt_alimento;
    }

}
