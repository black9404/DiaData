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

    @Query("Select * from meal")
    List<Meal> getRefeicoes();

    @Insert
    void addRefeicao(Meal meal);

    @Update
    void updateRefeicao(Meal meal);

    @Delete
    void deleteRemoveRefeicao(Meal meal);

}
