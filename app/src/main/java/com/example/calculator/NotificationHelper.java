package com.example.calculator;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("here","REached onRecieve");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Log.d("here","REached IF in onRecieve");
            NotificationCompat.Builder builder= new NotificationCompat.Builder(context,"Id");
            builder.setContentTitle("Notifcation");
            builder.setContentText("You received a Notification");
            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setAutoCancel(true);
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

            NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);

            notificationManagerCompat.notify(1,builder.build());
        }
    }

}


