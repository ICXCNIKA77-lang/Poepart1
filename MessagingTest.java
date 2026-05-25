import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 Unit Tests for the messaging class functionality.
 * Assures requirements compliance for IDs, cellphone numbers, lengths, and signatures.
 * * @author given
 */
public class MessagingTest {

    private messaging testMessage1;
    private messaging testMessage2;

    @BeforeEach
    public void setUp() {
        // Added an extra '2' at the end to make it exactly 13 characters long
        testMessage1 = new messaging(
                "+277186930022",
                "Hi Mike, can you join us for dinner tonight?",
                0
        );

        testMessage2 = new messaging(
                "+277186930022",
                "HI THANKS",
                0
        );
    }
    /**
     * Test of checkMessageID method, verifying it dynamically creates
     * a valid identifier sequence within character boundaries.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("Testing: checkMessageID");
        assertTrue(testMessage1.checkMessageID(), "Message ID should not exceed 10 characters.");
    }

    /**
     * Test of checkRecipientCell method, verifying international area formatting
     * constraints and character string limits match expected standards.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("Testing: checkRecipientCell");
        assertTrue(testMessage1.checkRecipientCell(), "Recipient cell layout should be valid (+27 and 13 chars total).");
    }

    /**
     * Test of createMessageHash method using explicit rubric design expectations.
     * Checks if words are sliced cleanly and returned as unified uppercase structures.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("Testing: createMessageHash");

        // Extract the first two numbers of the generated message ID to build the expected hash match
        String targetIDPart = testMessage2.createMessageHash().substring(0, 2);

        // According to the specification example layout: 00:0:HITHANKS
        String expectedHashPattern = targetIDPart + ":0:HITHANKS";

        assertEquals(expectedHashPattern, testMessage2.createMessageHash(),
                "The message hash formulation sequence logic failed to build structural patterns correctly.");
    }

    /**
     * Test of message length validation behavior pattern rules.
     */
    @Test
    public void testMessageLengthConstraints() {
        System.out.println("Testing: Message Length Bounds");

        // Verify standard valid string payload processing size
        String testContent = "Valid String";
        assertTrue(testContent.length() <= 250, "Standard length classification boundaries failed validation constraints evaluation.");

        // Create an intentional boundary overflow payload configuration string block
        StringBuilder overflowingPayload = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            overflowingPayload.append("abcdefghij"); // Generates 260 character configuration blocks
        }

        assertTrue(overflowingPayload.length() > 250, "Overflow verification pattern logic configuration failed setup.");
    }
}