package com.android.guillaume.mynews;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.LocalBroadcastManager;

import com.android.guillaume.mynews.utils.JobReceiver;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

@MediumTest
@RunWith(AndroidJUnit4.class)

public class IntegrationTestSendNotification {

    @Test
    public void send_Notification_When_Receiver_Get_Intent() {

        JobReceiver receiver = new JobReceiver();

        // User Selection
        String query = "Trump";
        ArrayList<String> filterQuery = new ArrayList<>();
        filterQuery.add("world");
        filterQuery.add("business");
        filterQuery.add("politics");

        // Get Context
        Context context = InstrumentationRegistry.getTargetContext();

        //action name from maninfest file
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, new IntentFilter(".utils.JobReceiver"));

        // Create Intent
        Intent intent = new Intent(".utils.JobReceiver");
        intent.putExtra("QUERY", query);
        intent.putStringArrayListExtra("FILTER",filterQuery);

        // Send Broadcast with intent
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Valide if true
        assertTrue(receiver.receiveBroadcastStatus());

        // Unregister Receiver
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
        assertTrue(true);
    }
}