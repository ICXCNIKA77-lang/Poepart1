import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Poepart3Test { // Renamed the class to match

    @BeforeEach
    public void setUp() {
        // Clear arrays using Poepart3
        Poepart3.sentMessages.clear();
        Poepart3.disregardedMessages.clear();
        Poepart3.storedMessages.clear();
        Poepart3.messageHashArray.clear();
        Poepart3.messageIDArray.clear();
        Poepart3.recipientArray.clear();
        Poepart3.senderArray.clear();

        // Populate arrays using Poepart3
        Poepart3.addMessageToParallelArrays("11111", "HASH1", "+27834557896", "Did you get the cake?", "Sent", "Developer");
        Poepart3.addMessageToParallelArrays("22222", "HASH2", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored", "Developer");
        Poepart3.addMessageToParallelArrays("33333", "HASH3", "+27834484567", "Yohoooo, I am at your gate.", "Disregarded", "Developer");
        Poepart3.addMessageToParallelArrays("0838884567", "HASH4", "+27831111111", "It is dinner time!", "Sent", "Developer");
        Poepart3.addMessageToParallelArrays("55555", "HASH5", "+27838884567", "Ok, I am leaving without you.", "Stored", "Developer");
    }

    @Test
    public void testSentMessagesCorrectlyPopulated() {
        String report = Poepart3.displaySenderAndRecipientStored();
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time!"));
    }

    @Test
    public void testDisplayLongestMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        String actual = Poepart3.displayLongestStoredMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchForMessageID() {
        String expected = "\"It is dinner time!\"";
        String actual = Poepart3.searchMessageByID("0838884567");
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchMessagesRegardingRecipient() {
        String actual = Poepart3.searchMessagesByRecipient("+27838884567");
        assertTrue(actual.contains("\"Where are you? You are late! I have asked you to be on time.\""));
        assertTrue(actual.contains("\"Ok, I am leaving without you.\""));
    }

    @Test
    public void testDeleteMessageUsingHash() {
        String expected = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        String actual = Poepart3.deleteMessageByHash("HASH2");
        assertEquals(expected, actual);

        assertEquals("Hash not found.", Poepart3.deleteMessageByHash("HASH2"));
    }

    @Test
    public void testDisplayReport() {
        String report = Poepart3.displayFullReport();
        assertTrue(report.contains("Message Hash: HASH1"));
        assertTrue(report.contains("Recipient: +27834557896"));
        assertTrue(report.contains("Message: Did you get the cake?"));
    }
}