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
        Calendar calendar = Calendar.getInstance();
        //Define tempo
        calendar.set(Calendar.HOUR_OF_DAY, 3);
        calendar.set(Calendar.MINUTE, 3);
        calendar.set(Calendar.SECOND, 0);

        //Intent
        Intent intentNot = new Intent(getApplicationContext(), Notification_reciever.class);
        intentNot.setAction("notification_time");
        PendingIntent pendingIntentNot = PendingIntent.getBroadcast(getApplicationContext(), 69, intentNot, PendingIntent.FLAG_UPDATE_CURRENT);

        //Cria a notificação todos os dias de acordo com o calendario acima definido
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntentNot);
    }
}
