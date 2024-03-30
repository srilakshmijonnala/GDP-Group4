package com.example.wholesalesystem.helper;

package com.example.wholesalesystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.wholesalesystem.helper.MessageModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

class ChatActivityTest {

    private ChatActivity chatActivity;

    @BeforeEach
    void setUp() {
        chatActivity = new ChatActivity();
    }

    @Test
    void testPreconditions() {
        assertNotNull(chatActivity);
    }

    @Test
    void testSendMessage() {
        // Simulating sending a message
        chatActivity.chat.setText("Test message");
        chatActivity.onClickSendButton(); // Simulate clicking send button

        // Assert that the message is added to the chat list
        ArrayList<MessageModel> chatList = chatActivity.chaa;
        assertNotNull(chatList);
        assertEquals(1, chatList.size());

        // Assert the message content
        MessageModel sentMessage = chatList.get(0);
        assertEquals("Test message", sentMessage.getMessage());
        assertEquals(chatActivity.uname, sentMessage.getUsername());
        assertEquals(true, sentMessage.isSender());
        assertNotNull(sentMessage.getCreatedAt());
    }

    // Add more test cases as needed for other methods and functionalities of ChatActivity
}
