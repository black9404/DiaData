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
        defineTimeNotification();

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

        for(int f=1; f < 5; f++) {
            Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);
            intent.setAction("notification_time");
            PendingIntent intentP = PendingIntent.getBroadcast(getApplicationContext(), f, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            calendar.set(Calendar.HOUR_OF_DAY, sp.getInt("H"+f,-1));
            calendar.set(Calendar.MINUTE, sp.getInt("M"+f,-1));
            calendar.set(Calendar.SECOND, sp.getInt("S"+f,-1));

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, intentP);
        }

        /*//Intent
        Intent intentNot = new Intent(getApplicationContext(), Notification_reciever.class);
        intentNot.setAction("notification_time");
        PendingIntent pendingIntentNot = PendingIntent.getBroadcast(getApplicationContext(), 69, intentNot, PendingIntent.FLAG_UPDATE_CURRENT);

        //Cria a notificação todos os dias de acordo com o calendario acima definido
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntentNot);*/
    }

    //Definição dos Horários
    private void defineTimeNotification(){
        SharedPreferences sp = getSharedPreferences("horarios", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        //Horário 1
        editor.putInt("H1", 7);
        editor.putInt("M1", 0);
        editor.putInt("S1", 0);
        //Horário 2
        editor.putInt("H2", 10);
        editor.putInt("M2", 0);
        editor.putInt("S2", 0);
        //Horário 3
        editor.putInt("H3", 13);
        editor.putInt("M3", 0);
        editor.putInt("S3", 0);
        //Horário 4
        editor.putInt("H4", 16);
        editor.putInt("M4", 0);
        editor.putInt("S4", 0);
        //Horário 5
        editor.putInt("H5", 19);
        editor.putInt("M5", 24);
        editor.putInt("S5", 0);
        editor.apply();
    }

}
