package com.example.wholesalesystem.helper;

package com.example.wholesalesystem.admin;

import android.content.Intent;
import android.os.Build;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.wholesalesystem.R;
import com.example.wholesalesystem.helper.MessageAdapter;
import com.example.wholesalesystem.helper.MessageModel;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;

import static android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class AdminChatActivityTest {

    private ActivityScenario<AdminChatActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(AdminChatActivity.class);
    }

    @Test
    public void testAdminChatActivityNotNull() {
        activityScenario.onActivity(activity -> {
            assertNotNull(activity);
        });
    }

    @Test
    public void testSendMessage() {
        activityScenario.onActivity(activity -> {
            EditText chatEditText = activity.findViewById(R.id.edit_gchat_message);
            ImageButton sendButton = activity.findViewById(R.id.button_gchat_send);

            assertNotNull(chatEditText);
            assertNotNull(sendButton);

            FirebaseFirestore mockFirestore = FirebaseFirestore.getInstance();
            assertNotNull(mockFirestore);

            activity.runOnUiThread(() -> {
                chatEditText.setText("Test Message");
                sendButton.performClick();
            });

            // Replace with assertions based on the success of sending a message to Firestore
        });
    }

    @Test
    public void testIntentData() {
        activityScenario.onActivity(activity -> {
            Intent intent = new Intent();
            intent.putExtra("Username", "Test User");
            intent.putExtra("Uid", "Test Uid");
            activity.setIntent(intent);

            TextView nameTextView = activity.findViewById(R.id.name);
            assertNotNull(nameTextView);
            assertEquals("Test User", nameTextView.getText().toString());
        });
    }

    // Add more test cases as needed
}
