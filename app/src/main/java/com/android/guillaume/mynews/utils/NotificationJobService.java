package com.android.guillaume.mynews.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Calendar;

public class NotificationJobService {

    private Context context;

    public NotificationJobService(Context context) {
        this.context = context;
    }

    public void createJob(String query, ArrayList<String> filterQuery){
        Calendar calCurrent = Calendar.getInstance();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 11);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 1);

        //verify if current time isn't superior to notification time
        if(cal.before(calCurrent))
            cal.add(Calendar.DATE,1);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, JobReceiver.class);
        intent.putExtra("QUERY", query);
        intent.putStringArrayListExtra("FILTER",filterQuery);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
    }

    public void cancelJob() {
        Intent intent = new Intent(context, JobReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pi);
    }
}
