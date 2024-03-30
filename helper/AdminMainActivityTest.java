package com.example.wholesalesystem.helper;

package com.example.wholesalesystem.admin;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.wholesalesystem.LoginActivity;
import com.example.wholesalesystem.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class AdminMainActivityTest {

    private ActivityScenario<AdminMainActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(AdminMainActivity.class);
    }

    @Test
    public void testAdminMainActivityNotNull() {
        activityScenario.onActivity(activity -> {
            assertNotNull(activity);
        });
    }

    @Test
    public void testSignOutButton() {
        activityScenario.onActivity(activity -> {
            Button signOutBtn = activity.findViewById(R.id.signOutBtn);
            assertNotNull(signOutBtn);

            activity.runOnUiThread(() -> {
                signOutBtn.performClick();
                Intent expectedIntent = new Intent(activity, LoginActivity.class);
                Intent actualIntent = activity.getNextStartedActivity();
                assertNotNull(actualIntent);
                assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
            });
        });
    }

    @Test
    public void testButtonClicks() {
        activityScenario.onActivity(activity -> {
            View customerButton = activity.findViewById(R.id.customer);
            assertNotNull(customerButton);
            customerButton.performClick();

            View productButton = activity.findViewById(R.id.product);
            assertNotNull(productButton);
            productButton.performClick();

            View ordersButton = activity.findViewById(R.id.orders);
            assertNotNull(ordersButton);
            ordersButton.performClick();

            View profitManageButton = activity.findViewById(R.id.proofitmanage);
            assertNotNull(profitManageButton);
            profitManageButton.performClick();

            // Add assertions based on the expected behavior of button clicks
        });
    }

    // Add more test cases as needed
}
}
