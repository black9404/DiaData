package com.example.android.diadata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.android.diadata.db.DiaDataDatabase;
import com.example.android.diadata.ui.Dashboard;
import com.example.android.diadata.ui.NewMeasurement;
import com.example.android.diadata.ui.UserDataForm;

public class MainActivity extends AppCompatActivity {

    public static DiaDataDatabase diaDataDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diaDataDatabase = Room.databaseBuilder(getApplicationContext(), DiaDataDatabase.class, "DiaDataDatabase").allowMainThreadQueries().build();

        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean userDataAdded = sharedPreferences.getBoolean("userDataAdded", false);
        String menuFragment = getIntent().getStringExtra("openFragment");

        if (!userDataAdded) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserDataForm()).commit();
        } else if (menuFragment != null) {

            if (menuFragment.equals("newDose")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewMeasurement()).commit();
            }

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Dashboard()).commit();
        }

    }
}
