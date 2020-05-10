package com.example.android.diadata.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food")
public class Food {

    @PrimaryKey(autoGenerate = true)
    int id_alimento;
    @ColumnInfo(name = "nome")
    String nome;
    @ColumnInfo(name = "hidratos")
    String hidratos;
    @ColumnInfo(name = "tipo_alimento")
    boolean tipo_alimento;
    @ColumnInfo(name = "quantidade_alimento")
    int quantidade_alimeto;

}
