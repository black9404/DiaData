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
    private boolean t_alimento;
    @ColumnInfo(name = "quantidade_alimento")
    private int qnt_alimento;

    public Food(int id_food, String food_name, int hidratos, boolean t_alimento, int qnt_alimento) {
        this.id_food = id_food;
        this.food_name = food_name;
        this.hidratos = hidratos;
        this.t_alimento = t_alimento;
        this.qnt_alimento = qnt_alimento;
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

    public boolean isT_alimento() {
        return t_alimento;
    }

    public void setT_alimento(boolean t_alimento) {
        this.t_alimento = t_alimento;
    }

    public int getQnt_alimento() {
        return qnt_alimento;
    }

    public void setQnt_alimento(int qnt_alimento) {
        this.qnt_alimento = qnt_alimento;
    }
}
