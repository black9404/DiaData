package com.example.android.diadata.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food")
public class Food {

    @PrimaryKey(autoGenerate = true)
    public int id_food;
    @ColumnInfo(name = "nome")
    public String food_name;
    @ColumnInfo(name = "hidratos")
    public int hidratos;
    @ColumnInfo(name = "tipo_alimento")
    public boolean t_alimento;
    @ColumnInfo(name = "quantidade_alimento")
    public int qnt_alimento;

    public Food(int id_food, String food_name, int hidratos, boolean t_alimento, int qnt_alimento) {
        this.id_food = id_food;
        this.food_name = food_name;
        this.hidratos = hidratos;
        this.t_alimento = t_alimento;
        this.qnt_alimento = qnt_alimento;
    }
}
