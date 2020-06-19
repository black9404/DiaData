package com.example.android.diadata;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class Notification_reciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notification_repeating = new Intent(context, MainActivity.class);
        notification_repeating.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 69, notification_repeating, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notif_insulina")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.app_icon)
                .setContentText("Se lês é porque a notificação por tempo esta a funcionar")
                .setContentTitle("Experiencia Notification-Tempo")
                .setAutoCancel(true);

        if (intent.getAction().equals("notification_time")) {
            notificationManager.notify(69,builder.build());
            Log.i("Notify", "Alarm Starts"); //Debugging
        }
    }
}
