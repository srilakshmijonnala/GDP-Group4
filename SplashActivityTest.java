package com.example.wholesalesystem.helper;


import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import static android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class SplashActivityTest {

    private ActivityScenario<SplashActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(SplashActivity.class);
    }

    @Test
    public void testSplashActivityNotNull() {
        activityScenario.onActivity(activity -> {
            assertNotNull(activity);
        });
    }

    @Test
    public void testSplashActivityIntent() {
        activityScenario.onActivity(activity -> {
            Handler handler = activity.handler;
            assertNotNull(handler);
            assertEquals(3000, handler.getDelay());

            Intent expectedIntent = new Intent(activity, LoginActivity.class);
            Intent actualIntent = activity.getIntent();
            assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
        });
    }

    @Test
    public void testStatusBarColor() {
        activityScenario.onActivity(activity -> {
            Window window = activity.getWindow();
            assertNotNull(window);

            int expectedStatusBarColor = ContextCompat.getColor(activity, R.color.white);
            int actualStatusBarColor = window.getStatusBarColor();
            assertEquals(expectedStatusBarColor, actualStatusBarColor);
        });
    }

    @Test
    public void testSystemBarsAppearance() {
        activityScenario.onActivity(activity -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Window window = activity.getWindow();
                assertNotNull(window);

                WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, window.getDecorView());
                assertNotNull(controller);

                int actualSystemBarsAppearance = controller.getSystemBarsAppearance();
                assertEquals(APPEARANCE_LIGHT_STATUS_BARS, actualSystemBarsAppearance);
            }
        });
    }

    @Test
    public void testSplashActivityFinish() {
        activityScenario.onActivity(activity -> {
            activityScenario.moveToState(Lifecycle.State.RESUMED);
            activityScenario.moveToState(Lifecycle.State.DESTROYED);
            assertFalse(activity.isFinishing());
        });
    }
}
