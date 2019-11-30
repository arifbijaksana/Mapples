package com.haerul.mapples.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.haerul.mapples.R;
import com.haerul.mapples.data.db.MasterDatabase;
import com.haerul.mapples.data.db.repository.MasterRepository;
import com.haerul.mapples.ui.MainActivity;

import java.io.IOException;
import java.util.UUID;

import static android.app.Notification.VISIBILITY_PRIVATE;
import static androidx.core.app.NotificationCompat.PRIORITY_MIN;

public class AppService extends Service {

    private Notification notification;
    private NotificationManager notificationManager;

    private String CHANNEL_ID = "my_channel_01";// The id of the channel.
    private CharSequence name = "SHD";// The user-visible name of the channel.
    private CharSequence name2 = "SiHanDist";// The user-visible name of the channel.
    private int importance = NotificationManager.IMPORTANCE_NONE;
    private int importance_high = NotificationManager.IMPORTANCE_HIGH;
    private NotificationChannel mChannel = null;
    private int notifId = 101;

    private Handler handler ;
    private Runnable runnable;
    private int delayMillis = 60000;
    private MasterRepository repository;

    private static boolean isRunning;
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand (Intent intent, int flags, int startId) {

        if (intent != null && intent.getAction() != null) {
            Log.w("getAction", intent.getAction());
            if (intent.getAction().equals(MainActivity.STARTFOREGROUND_ACTION)) {
                startServiceOreoCondition();
            } else if (intent.getAction().equals(MainActivity.STOPFOREGROUND_ACTION)) {
                Log.w("Stop", "stop");
                stopForeground(true);
                this.stopSelf();
            }
        }
        handler.postDelayed(runnable, 6000);
        return Service.START_STICKY; //super.onStartCommand(intent, flags, startId);/;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = true;
        Log.d("SERVICE" , "onCreate : " + Util.getTimestampNow());
        repository = new MasterRepository(MasterDatabase.getDatabase(getBaseContext()));
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        handler = new Handler();
        runnable = () -> {
            try {
                if (Util.getBooleanPreference(getBaseContext(), Constants.IS_LOGIN)) {
                    sync();
                    handler.postDelayed(runnable, delayMillis);
                } else {
                    if (AppService.isRunning()) {
                        Intent serviceIntent = new Intent(this, AppService.class);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            Log.w("stop", "foreground");
                            serviceIntent.setAction(MainActivity.STOPFOREGROUND_ACTION);
                            stopService(serviceIntent);
                        } else {
                            stopService(serviceIntent);
                            Log.w("stop", "background");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (AppService.isRunning()) {
                    Intent serviceIntent = new Intent(this, AppService.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Log.w("stop", "foreground");
                        serviceIntent.setAction(MainActivity.STOPFOREGROUND_ACTION);
                        stopService(serviceIntent);
                    } else {
                        stopService(serviceIntent);
                        Log.w("stop", "background");
                    }
                }
            }
        };
    }

    public static boolean isRunning() {
        return isRunning;
    }

    private Notification createNotification(String message) {
        Intent intent = null;
        PendingIntent pendingIntent = null;
        intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("⚡ MAPPLES")
                .setContentText(message)
                .setContentIntent(pendingIntent);

        return notificationBuilder.setChannelId(CHANNEL_ID).build();
    }

    private Notification createNotificationNew(String message) {
        Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        MediaPlayer mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(this, defaultRingtoneUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp)
                {
                    mp.release();
                }
            });
            mediaPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Intent intent = null;
        intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("tag", 1);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(500);
        v.vibrate(1000);
        v.vibrate(500);
        
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.logo))
                .setContentTitle("⚠ SiHanDist")
                .setContentText(message)
                /*.addExtras(addData)*/
                .setContentIntent(pendingIntent)
                .setLights(Color.RED, 3000, 3000)
                .setVibrate(new long[]{500, 500, 500, 500, 500, 500, 500, 500});
        notificationBuilder.setSound(defaultRingtoneUri);

        Notification notification = notificationBuilder.setChannelId(CHANNEL_ID).build();
        notification.ledARGB = 0xFFff0000;
        notification.flags = Notification.FLAG_SHOW_LIGHTS;
        notification.ledOnMS = 100;
        notification.ledOffMS = 100;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults|= Notification.DEFAULT_SOUND;
        notification.defaults|= Notification.DEFAULT_LIGHTS;
        notification.defaults|= Notification.DEFAULT_VIBRATE;
        return notification;
    }

    private void launchNotification(String message, int notifId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationManager.createNotificationChannel(mChannel);
            notification = createNotification(message);
            notificationManager.notify(notifId, notification);
        } else {
            notification = createNotification(message);
            notificationManager.notify(notifId, notification);
        }
    }

    private void launchNotificationNew(String channel, String message, int notifId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(channel, name2, importance_high);
            notificationManager.createNotificationChannel(mChannel);
            notification = createNotificationNew(message);
            notificationManager.notify(notifId, notification);
        } else {
            notification = createNotificationNew(message);
            notificationManager.notify(notifId, notification);
        }
    }

    @Override
    public void onDestroy() {
        Log.w("TAG", "SERVICE REMOVED");
        isRunning = false;
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }
    
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        System.out.println("onTaskRemoved called");
        Log.w(String.valueOf(this), "Service stop, called broadcast receiver");
        super.onTaskRemoved(rootIntent);
        
        /*Intent intent = new Intent(AppService.this, AppBroadcastReceiver.class);
        sendBroadcast(intent);*/
    }

    private void startServiceOreoCondition() {
        if (Build.VERSION.SDK_INT >= 26) {

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setLockscreenVisibility(VISIBILITY_PRIVATE);
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setCategory(Notification.CATEGORY_SYSTEM)
                    .setSmallIcon(R.drawable.logo)
                    .setPriority(PRIORITY_MIN)
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle("SHD")
                    .setAutoCancel(true)
                    .setOngoing(false)
                    .build();

            startForeground(notifId, notification);
            launchNotification("Tap to open the application.", notifId);
        }
    }
    
    
    //===============================================

    public void sync() {
        Log.w("-->", "sync " + UUID.randomUUID().toString().toUpperCase() + " time -> " + Util.getTimestampNow());
    }
}
