package com.example.wholesalesystem.helper;

package com.example.wholesalesystem.admin;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ListView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.wholesalesystem.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class AllUserActivityTest {

    private ActivityScenario<AllUserActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(AllUserActivity.class);
    }

    @Test
    public void testAllUserActivityNotNull() {
        activityScenario.onActivity(activity -> {
            assertNotNull(activity);
        });
    }

    @Test
    public void testListViewItemClick() {
        activityScenario.onActivity(activity -> {
            ListView listView = activity.findViewById(R.id.list);
            assertNotNull(listView);

            // Mock Firestore query snapshot
            QuerySnapshot querySnapshot = mock(QuerySnapshot.class);
            DocumentSnapshot documentSnapshot = mock(DocumentSnapshot.class);
            when(documentSnapshot.getString("username")).thenReturn("Test User");
            when(documentSnapshot.getId()).thenReturn("testUserId");
            when(querySnapshot.isEmpty()).thenReturn(false);
            when(querySnapshot.getDocuments()).thenReturn(new ArrayList<DocumentSnapshot>(){{add(documentSnapshot);}});
            FirebaseFirestore.getInstance().collection("Customers").onSuccess(querySnapshot);

            // Simulate item click
            activity.runOnUiThread(() -> {
                View itemView = listView.getChildAt(0);
                assertNotNull(itemView);
                itemView.performClick();
                Intent expectedIntent = new Intent(activity, AdminChatActivity.class);
                expectedIntent.putExtra("Uid", "testUserId");
                expectedIntent.putExtra("Username", "Test User");
                Intent actualIntent = activity.getNextStartedActivity();
                assertNotNull(actualIntent);
                assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
            });
        });
    }

    // Add more test cases as needed
}
