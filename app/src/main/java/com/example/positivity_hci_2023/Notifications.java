package com.example.positivity_hci_2023;


import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import java.util.List;

public class Notifications {
    private String channelId = "my_channel_1";
    private CharSequence channelName = "My Channel";

    public Notifications(Context context) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        CharSequence textTitle = "Positivity";
        long screenTime = totalScreenTime(context);
        long seconds = screenTime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60);
        CharSequence textContent;

        if (screenTime == 0)
            requestUsageStatsPermission(context);

        if (screenTime == 0) {
            textContent = "You've been on your phone for too long";
        } else {
            textContent = "You've been on your phone for " + formattedTime + "";
        }

        // Clicking opens the app
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);

        // Create notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.baseline_arrow_back_24)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        // Display notification
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d("Notifications", "Notifications: lacking permissions");
            return;
        }
        notificationManager.notify(0, builder.build());
    }


    private long totalScreenTime(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);

            long startTime = System.currentTimeMillis() - 1000 * 60 * 60 * 24; // Adjust the time frame as needed
            long endTime = System.currentTimeMillis();

            List<UsageStats> stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);

            long totalScreenTime = 0;

            for (UsageStats usageStats : stats) {
                totalScreenTime += usageStats.getTotalTimeInForeground();
            }
            return totalScreenTime;
        } else {
            return 0;
        }

    }

    private void requestUsageStatsPermission(Context context) {
        int REQUEST_USAGE_STATS = 1;
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivityForResult((Activity) context,intent, REQUEST_USAGE_STATS, new Bundle());
    }


}
