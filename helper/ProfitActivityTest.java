package com.example.wholesalesystem.helper;

package com.example.wholesalesystem.admin;

import android.os.Build;
import android.view.Window;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class ProfitActivityTest {

    private ActivityScenario<ProfitActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(ProfitActivity.class);
    }

    @Test
    public void testProfitActivityNotNull() {
        activityScenario.onActivity(activity -> {
            assertNotNull(activity);
        });
    }

    @Test
    public void testProfitActivityWindowFeature() {
        activityScenario.onActivity(activity -> {
            Window window = activity.getWindow();
            assertNotNull(window);
            assertEquals(Window.FEATURE_NO_TITLE, window.getAttributes().flags & Window.FEATURE_NO_TITLE);
        });
    }

    @Test
    public void testProfitActivityStatusBarColor() {
        activityScenario.onActivity(activity -> {
            Window window = activity.getWindow();
            assertNotNull(window);
            assertEquals(APPEARANCE_LIGHT_STATUS_BARS, window.getDecorView().getSystemUiVisibility() & APPEARANCE_LIGHT_STATUS_BARS);
        });
    }

    // Add more test cases as needed
}

