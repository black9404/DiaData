package com.example.android.diadata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.android.diadata.db.DiaDataDatabase;
import com.example.android.diadata.ui.Dashboard;
import com.example.android.diadata.ui.NewMeasurement;
import com.example.android.diadata.ui.UserDataForm;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static DiaDataDatabase diaDataDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNotificationTime();

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

    //metodo para programar notificações por tempo
    private void setNotificationTime(){
        SharedPreferences sp = getSharedPreferences("horarios", MODE_PRIVATE);
        Calendar calendar = Calendar.getInstance();


            Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);
            intent.setAction("notification_time");
            PendingIntent intentP = PendingIntent.getBroadcast(getApplicationContext(), 5, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 39);
            calendar.set(Calendar.SECOND, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intentP);
    }
}

