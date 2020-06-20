package com.example.android.diadata.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.diadata.db.model.Food;

import java.util.List;

@Dao
public interface FoodDao {

    /*@Query("Select nome from food")
    List<Food> getAlimentos();*/

    //Buscar Hidratos
    @Query("SELECT hidratos from food WHERE nome = :nome_alimento")
    double getHidratosAlimento(String nome_alimento);

    //Buscar todos os alimentos
    @Query("SELECT DISTINCT nome FROM food")
    List<String> getNomesAlimento();

    //Buscar id do alimento
    @Query("Select id_food from food WHERE nome = :nome_alimento")
    int getIdFood(String nome_alimento);

    //Buscar todos os tipos de alimentos existentes
    @Query("SELECT tipo_alimento FROM food WHERE nome =:nome_alimento")
    int getTipoAlimento(String nome_alimento);

    //Buscar todos os alimentos correspondestes ao tipo selecionado
    @Query("SELECT nome from food WHERE tipo_alimento = :t_alimento")
    String getNomePorTipoAlimento(int t_alimento);

    @Insert
    void addAlimento(Food food);

    @Update
    void updateAlimento(Food food);

    @Delete
    void deleteAlimento(Food food);

}
