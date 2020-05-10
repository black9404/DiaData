package com.example.android.diadata.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal",
        primaryKeys = {"id_meal"}, foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "id_user")})
public class Meal {

    @PrimaryKey(autoGenerate = true)
    int id_meal;
    @ColumnInfo(name = "id_user")
    int id_user;
    @ColumnInfo(name = "qnt_hidratos")
    int hidratos;
    @ColumnInfo(name = "nome_meal")
    String nome;

    public Meal(int id_meal, int id_user, int hidratos, String nome) {
        this.id_meal = id_meal;
        this.id_user = id_user;
        this.hidratos = hidratos;
        this.nome = nome;
    }

    @Ignore
    public Meal(int id_user, int hidratos, String nome) {
        this.id_user = id_user;
        this.hidratos = hidratos;
        this.nome = nome;
    }

}
