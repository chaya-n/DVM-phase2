package com.example.calculator;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper extends Service {

    Handler handler;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notifyFunction("a");
        handler=new Handler();
        handler.postDelayed(runnable, 7000);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            notifyFunction("q");
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void notifyFunction(String s){
        Log.d("hi","Reached inside NotifyFunction");
        NotificationCompat.Builder builder=new NotificationCompat.Builder(NotificationHelper.this,"Id");
        builder.setContentTitle("Notifcation");
        builder.setContentText("You received a Notification");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setAutoCancel(true);



        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(NotificationHelper.this);
        managerCompat.notify(1,builder.build());

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("Id","Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

    }
}


