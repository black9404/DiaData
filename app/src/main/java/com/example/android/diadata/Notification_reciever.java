package com.example.android.diadata;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.time.LocalDateTime;



public class Notification_reciever extends BroadcastReceiver {
    boolean aux = false;

    @Override
    public void onReceive(Context context, Intent intent) {

    if (!aux){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notification_repeating = new Intent(context, MainActivity.class);
        notification_repeating.putExtra("openFragment", "newDose");
        notification_repeating.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 5, notification_repeating, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notif_insulina")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.app_icon)
                .setContentTitle("Hora da pica")
                .setContentText("Clica em mim e vamos ver os teus valores de Glicemia!")
                .addAction(R.drawable.ic_add, "Tomar Dose", pendingIntent)
                .setAutoCancel(true);

        if (intent.getAction().equals("notification_time")) {
            notificationManager.notify(5, builder.build());
            Log.i("Notify", "Alarm Starts"); //Debugging
        }
        aux = true;
    }
    }
}
