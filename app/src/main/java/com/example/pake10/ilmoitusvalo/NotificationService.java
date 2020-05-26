package com.example.pake10.ilmoitusvalo;

import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.support.v4.content.LocalBroadcastManager;

import java.io.IOException;


public class NotificationService extends NotificationListenerService {

    Context context;

    @Override

    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();

    }
    @Override

    public void onNotificationPosted(StatusBarNotification sbn) {

        KeyguardManager myKM = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
        wakeLock.acquire();

        while(myKM.inKeyguardRestrictedInputMode()) {
            try {
                Process process = Runtime.getRuntime().exec(new String[]{"su", "-c", "l=1;while [ $l -ne 32 ] ;do echo $l >/sys/class/leds/charging/brightness; l=`expr $l + 1`;sleep 0.01;done;sleep 0.5;while [ $l -ne 0 ] ;do  l=`expr $l - 1`;echo $l >/sys/class/leds/charging/brightness;sleep 0.01;done; sleep 3;"}); // Blink the led.
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        wakeLock.release();
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

    }
}
