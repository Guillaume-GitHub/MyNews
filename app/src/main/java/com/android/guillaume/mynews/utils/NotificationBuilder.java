package com.android.guillaume.mynews.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.activities.MainActivity;
import com.android.guillaume.mynews.models.articleSearch.ArticleSearchResult;

public class NotificationBuilder {

    private Context context;
    private static String CHANNEL_ID = "NEWS_APP";

    public NotificationBuilder(Context context) {
        this.context = context;
        createNotificationChannel();
    }

    // Create a Notification channel
    private void createNotificationChannel() {
        // Create the NotificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "CHANNEL_TEST";
            String description = "channel description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Create and Display Notification
    public void sendNotification(ArticleSearchResult articleSearchResult) {

        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context,0,intent,0);
        
        int result = articleSearchResult.getResponse().getMeta().getHits();
        String content;

        if (result > 0) content = "I find " + result + " new article(s), corresponding to your research";
        else content = "No more news today ....";

        NotificationCompat.Builder notification  = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(context.getResources().getString(R.string.app_name))
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pi)
                .setAutoCancel(true);

        notification.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        // unique int for each notification
        notificationManager.notify(1, notification.build());
    }
}
