package com.android.guillaume.mynews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.LayoutInflater;
import android.view.View;

import com.android.guillaume.mynews.controllers.activities.MainActivity;
import com.android.guillaume.mynews.views.MyViewHolder;
import com.android.guillaume.mynews.views.MyViewHolder$$ViewBinder;
import com.android.guillaume.mynews.views.RecyclerViewAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.android.guillaume.mynews", appContext.getPackageName());
    }
}
