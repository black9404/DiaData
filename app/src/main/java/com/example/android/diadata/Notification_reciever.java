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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notification_repeating = new Intent(context, MainActivity.class);
        notification_repeating.putExtra("openFragment", "newDose");
        notification_repeating.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // data/hora atual
        int h = LocalDateTime.now().getHour();


        //Horário 1
        if (h >= 7 & h <10){
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, notification_repeating, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notif_insulina")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentTitle("Hora do Pequeno Almoço")
                    .setContentText("Clica agora em mim e vamos ver os teus valores de Glicemia!")
                    .addAction(R.drawable.ic_add,"Tomar Dose", pendingIntent)
                    .setOngoing(true)
                    .setAutoCancel(true);

            if (intent.getAction().equals("notification_time")) {
                notificationManager.notify(1,builder.build());
                Log.i("Notify", "Alarm Starts"); //Debugging
            }
            //Horário 2
        }else if(h >= 10 & h <13){
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 2, notification_repeating, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notif_insulina")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentTitle("Hora do Meio da Manhã")
                    .setContentText("Clica agora em mim e vamos ver os teus valores de Glicemia!")
                    .addAction(R.drawable.ic_add,"Tomar Dose", pendingIntent)
                    .setOngoing(true)
                    .setAutoCancel(true);

            if (intent.getAction().equals("notification_time")) {
                notificationManager.notify(2, builder.build());
                Log.i("Notify", "Alarm Starts"); //Debugging
            }
            //Horário 3
        }else if(h >= 13 & h <16){
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 3, notification_repeating, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notif_insulina")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentTitle("Hora do Almoço")
                    .setContentText("Clica agora em mim e vamos ver os teus valores de Glicemia!")
                    .addAction(R.drawable.ic_add,"Tomar Dose", pendingIntent)
                    .setOngoing(true)
                    .setAutoCancel(true);

            if (intent.getAction().equals("notification_time")) {
                notificationManager.notify(3, builder.build());
                Log.i("Notify", "Alarm Starts"); //Debugging
            }
            //Horário 4
        }else if(h >= 16 & h <19){
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 4, notification_repeating, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notif_insulina")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentTitle("Hora de Lanchar")
                    .setContentText("Clica agora em mim e vamos ver os teus valores de Glicemia!")
                    .addAction(R.drawable.ic_add,"Tomar Dose", pendingIntent)
                    .setOngoing(true)
                    .setAutoCancel(true);

            if (intent.getAction().equals("notification_time")) {
                notificationManager.notify(4, builder.build());
                Log.i("Notify", "Alarm Starts"); //Debugging
            }
            //Horário 5
        }else{
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 5, notification_repeating, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notif_insulina")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentTitle("Hora do Jantar")
                    .setContentText("Clica agora em mim e vamos ver os teus valores de Glicemia!")
                    .addAction(R.drawable.ic_add,"Tomar Dose", pendingIntent)
                    .setOngoing(true)
                    .setAutoCancel(true);

            if (intent.getAction().equals("notification_time")) {
                notificationManager.notify(5, builder.build());
                Log.i("Notify", "Alarm Starts"); //Debugging
            }
        }
    }
}
