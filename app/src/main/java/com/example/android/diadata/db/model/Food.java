package com.example.android.diadata.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food")
public class Food {

    @PrimaryKey(autoGenerate = true)
    private int id_food;
    @ColumnInfo(name = "nome")
    private String food_name;
    @ColumnInfo(name = "hidratos")
    private int hidratos;
    @ColumnInfo(name = "tipo_alimento")
    private int t_alimento;

    public Food(int id_food, String food_name, int hidratos, int t_alimento) {
        this.id_food = id_food;
        this.food_name = food_name;
        this.hidratos = hidratos;
        this.t_alimento = t_alimento;
    }

    public int getId_food() {
        return id_food;
    }

    public void setId_food(int id_food) {
        this.id_food = id_food;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getHidratos() {
        return hidratos;
    }

    public void setHidratos(int hidratos) {
        this.hidratos = hidratos;
    }

    public int getT_alimento() {
        return t_alimento;
    }

    public void setT_alimento(int t_alimento) {
        this.t_alimento = t_alimento;
    }
}
