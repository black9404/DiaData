package com.example.android.diadata.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.diadata.db.model.Meal;

import java.util.List;

@Dao
public interface MealDao {

    @Query("SELECT DISTINCT nome_meal FROM meal")
    List<String> getRefeicoes();

    @Query("Select id_meal from meal WHERE nome_meal = :nome_refeicao")
    int getIdMeal(String nome_refeicao);

    //Vai buscar todos os ingredientes da refeição e executa a soma
    @Query("SELECT SUM(qnt_hidratos) FROM meal WHERE nome_meal = :nome_refeicao")
    double getSomaHidratos(String nome_refeicao);

    @Insert
    void addRefeicao(Meal meal);

    @Update
    void updateRefeicao(Meal meal);

    @Delete
    void deleteRemoveRefeicao(Meal meal);

}
