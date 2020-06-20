package com.example.android.diadata.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.diadata.db.model.DashData;

import java.util.List;

@Dao
public interface DashDataDao {

    @Query("SELECT SUM(hidratos) from dashdata")
    double getHidratosTotal();

    @Query("SELECT hora_info FROM DashData WHERE id_info = (SELECT MAX(id_info) FROM DashData)")
    int getUltimaDose();

    @Query("SELECT dia_info FROM DashData WHERE id_info = (SELECT MAX(id_info) FROM DashData)")
    int getUltimaDoseDia();

    @Query("SELECT SUM(dose) from dashdata")
    int getDosesTotal();

    @Insert
    void addDados(DashData DD);

    @Update
    void updateDados(DashData DD);

    @Delete
    void deleteRemoveDados(DashData DD);

}
