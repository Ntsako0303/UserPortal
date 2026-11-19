
package userportalpart3;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import userprotalpart3.Messages;
import userprotalpart3.StoredMessages;

public class StoredMessagesTest {

    private Messages message;

    @Before
    public void setUp() {

        // Clear the list before each test
        StoredMessages.storedMessages.clear();

        // Create a test message
        message = new Messages(
                "1234567890",
                1,
                "Ntsako",
                "+27821234567",
                "Hello World from QuickChat"
        );
    }

    @Test
    public void testAddStoredMessage() {

        StoredMessages.addStoredMessage(message);

        assertEquals(
                1,
                StoredMessages.storedMessages.size()
        );
    }

    @Test
    public void testDeleteMessageByHash() {

        StoredMessages.addStoredMessage(message);

        String hash = message.createMessageHash();

        StoredMessages.deleteMessageByHash(hash);

        assertEquals(
                0,
                StoredMessages.storedMessages.size()
        );
    }

    @Test
    public void testSearchMessageID() {

        StoredMessages.addStoredMessage(message);

        // Search using the message ID
        StoredMessages.searchMessageID("1234567890");

        assertEquals(
                1,
                StoredMessages.storedMessages.size()
        );
    }

    @Test
    public void testSearchByRecipient() {

        StoredMessages.addStoredMessage(message);

        // Search using the recipient number
        StoredMessages.searchByRecipient("+27821234567");

        assertEquals(
                1,
                StoredMessages.storedMessages.size()
        );
    }

    @Test
    public void testDisplaySenderAndRecipient() {

        StoredMessages.addStoredMessage(message);

        StoredMessages.displaySenderAndRecipient();

        assertEquals(
                1,
                StoredMessages.storedMessages.size()
        );
    }

    @Test
    public void testDisplayLongestStoredMessage() {

        StoredMessages.addStoredMessage(message);

        StoredMessages.displayLongestStoredMessage();

        assertEquals(
                1,
                StoredMessages.storedMessages.size()
        );
    }

    @Test
    public void testDisplayStoredMessages() {

        StoredMessages.addStoredMessage(message);

        StoredMessages.displayStoredMessages();

        assertEquals(
                1,
                StoredMessages.storedMessages.size()
        );
    }
}

