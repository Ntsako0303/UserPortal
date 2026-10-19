
package userportal;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessagesTest {

    private Messages instance;

    @Before
    public void setUp() {
        instance = new Messages(
                "1234567890",
                1,
                "+27821234567",
                "Hello World from QuickChat"
        );
    }

    /**
     * Test of generateMessageID method.
     */
    @Test
    public void testGenerateMessageID() {
        System.out.println("generateMessageID");

        String result = Messages.generateMessageID();

        assertNotNull(result);
        assertEquals(10, result.length());
        assertTrue(result.matches("\\d{10}"));
    }

    /**
     * Test of checkMessageID method.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");

        assertTrue(instance.checkMessageID());
    }

    /**
     * Test of checkRecipientCell method.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");

        assertTrue(
                Messages.checkRecipientCell("+27821234567")
        );

        assertFalse(
                Messages.checkRecipientCell("0821234567")
        );

        assertFalse(
                Messages.checkRecipientCell("")
        );

        assertFalse(
                Messages.checkRecipientCell(null)
        );
    }

    /**
     * Test of createMessageHash method.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");

        String result = instance.createMessageHash();

        assertEquals("12:1:HELLOWORLD", result);
    }

    /**
     * Test of sendMessage method.
     *
     * Interactive input is required for this method.
     * Test it manually through the QuickChat menu.
     */

    /**
     * Test of displaySentMessages method.
     */
    @Test
    public void testDisplaySentMessages() {
        System.out.println("displaySentMessages");

        // This method should run without throwing an exception.
        Messages.displaySentMessages();
    }

    /**
     * Test of returnTotalMessages method.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");

        int result = Messages.returnTotalMessages();

        assertTrue(result >= 0);
    }

    /**
     * Interactive menu methods are tested manually.
     *
     * startQuickChat() and main() require console input.
     * Running them as automated tests can cause the test
     * suite to wait for user input or interfere with other tests.
     */
}
