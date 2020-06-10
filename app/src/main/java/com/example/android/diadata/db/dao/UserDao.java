package com.example.android.diadata.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.diadata.db.model.User;

@Dao
public interface UserDao {

    @Query("SELECT nome FROM user")
    String getUserName();

    @Query("SELECT subnome FROM user")
    String getUserSubname();

    @Query("SELECT idade FROM user")
    int getIdade();

    @Query("SELECT tipo_diabetes FROM user")
    int getTDiabetes();

    @Query("SELECT genero FROM user")
    String getGenero();

    @Insert
    void addUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

}
