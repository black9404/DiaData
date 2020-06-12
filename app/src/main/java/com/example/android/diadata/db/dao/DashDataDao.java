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

    @Insert
    void addDados(DashData DD);

    @Update
    void updateDados(DashData DD);

    @Delete
    void deleteRemoveDados(DashData DD);

}
