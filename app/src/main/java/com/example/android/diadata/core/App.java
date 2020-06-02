package com.example.android.diadata.core;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.Objects;

public class App extends Application {

    public static final String CHANNEL_ID = "notif_insulina";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    private void createNotificationChannels() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notifInsulina = new NotificationChannel(CHANNEL_ID, "Notificações Insulina", NotificationManager.IMPORTANCE_HIGH);
            notifInsulina.setDescription("Notificações sobre insulina");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(notifInsulina);

        }

    }

}
