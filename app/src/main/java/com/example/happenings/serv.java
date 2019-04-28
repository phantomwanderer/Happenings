package com.example.happenings;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class serv extends Service {
    private NotificationManager mNotificationManager;

    private static String DEFAULT_CHANNEL_ID = "default_channel";
    private static String DEFAULT_CHANNEL_NAME = "Default";

    @Override
    public void onCreate() {
        SharedPreferences event= (SharedPreferences) getSharedPreferences("pref", Context.MODE_PRIVATE);

        //1.Get reference to Notification Manager
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel(mNotificationManager);
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);

//**add this line**
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

//**edit this line to put requestID as requestCode**
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //2.Build Notification with NotificationCompat.Builder

        Notification notification = new NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(event.getString("Description and Venue","")))
                .setContentTitle(event.getString("Event Name",""))   //Set the title of Notification
                .setContentText(event.getString("Time",""))    //Set the text for notification
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(contentIntent)//Set the icon
                .build();

        //Send the notification.
        mNotificationManager.notify(1, notification);
    }


    /*
     * Create NotificationChannel as required from Android 8.0 (Oreo)
     * */
    public void createNotificationChannel(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Create channel only if it is not already created
            if (notificationManager.getNotificationChannel(DEFAULT_CHANNEL_ID) == null) {
                notificationManager.createNotificationChannel(new NotificationChannel(
                        DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
                ));
            }
        }









        Handler handler=new Handler();
        Runnable r=new Runnable() {
            @Override
            public void run() {
                SharedPreferences event= (SharedPreferences) getSharedPreferences("pref", Context.MODE_PRIVATE);

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 0);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String yesterdayAsString = dateFormat.format(calendar.getTime());
                if(yesterdayAsString.equals(event.getString("Date","")))
                {
                    Toast.makeText(getApplicationContext(),"asdfasfa",Toast.LENGTH_LONG).show();
                }
            }
        };
        handler.postDelayed(r,1000);

        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        return super.onStartCommand(intent, flags, startId);
    }
}
