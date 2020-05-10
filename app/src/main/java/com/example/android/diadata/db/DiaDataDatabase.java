package com.example.android.diadata.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android.diadata.db.dao.FoodDao;
import com.example.android.diadata.db.dao.MealDao;
import com.example.android.diadata.db.dao.UserDao;
import com.example.android.diadata.db.model.Food;
import com.example.android.diadata.db.model.Meal;
import com.example.android.diadata.db.model.User;

@Database(entities = {User.class, Meal.class, Food.class}, version = 1)
public abstract class DiaDataDatabase extends RoomDatabase {
    private static final String DB_NAME = "DiaData_db";
    private static DiaDataDatabase instance;

    public static synchronized DiaDataDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DiaDataDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();

    public abstract MealDao mealDao();

    public abstract FoodDao foodDao();
}
