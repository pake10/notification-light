package com.example.pake10.ilmoitusvalo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;

public class OnBoot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            Intent serviceIntent = new Intent(context, NotificationService.class);
            context.startService(serviceIntent);
        }

    //   if (intent.getAction().equalsIgnoreCase(Intent.ACTION_USER_PRESENT)) {
    //       NotificationService.onReceive
     //  }
    }
}