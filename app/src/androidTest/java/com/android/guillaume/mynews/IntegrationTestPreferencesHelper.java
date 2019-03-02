package com.android.guillaume.mynews;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.widget.EditText;

import com.android.guillaume.mynews.utils.PreferencesHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class IntegrationTestPreferencesHelper {

    private static final String value = "Integration Test";
    private static final boolean booleanValue = true;
    private static final String KEY_PREF = "KEY_PREF";
    private PreferencesHelper sharedPref;

    @Before
    public void before() {
        Context context = InstrumentationRegistry.getTargetContext();
        sharedPref = new PreferencesHelper(context);
    }

    @Test
    public void put_String_Value_Into_Preference_And_Get_It() throws Exception {

        // Save in SharedPreference System
        sharedPref.saveInPreference(KEY_PREF,value);

        // Verify that the received data is correct.
        assertEquals("Integration Test", sharedPref.getFromPreference(KEY_PREF,""));
    }

    @Test
    public void put_Boolean_Value_Into_Preference_And_Get_It() throws Exception {

        // Save in SharedPreference System
        sharedPref.saveInPreference(KEY_PREF,booleanValue);

        // Verify that the received data is correct.
        assertTrue(sharedPref.getFromPreference(KEY_PREF,false));
    }
}
