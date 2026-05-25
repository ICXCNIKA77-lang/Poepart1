import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Messaging class handles all message-related functionality for QuickChat.
 * Includes message creation, validation, hashing, sending, storing, and retrieval.
 *
 * @author given
 */
public class messaging {

    // Instance variables
    private final String recipient;
    private final String messageContent;
    private final String messageID;
    private final int messageCount;

    // Static collections to hold sent messages during runtime context tracking
    private static final List<messaging> sentMessages = new ArrayList<>();
    private static int totalGlobalMessages = 0;

    /**
     * Creates a new message object.
     *
     * @param recipient      The recipient's cell number
     * @param messageContent The message text
     * @param count          The running message count (used in hash)
     */
    public messaging(String recipient, String messageContent, int count) {
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageCount = count;

        // Generate a random 10-digit tracking string
        Random rand = new Random();
        long num = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        this.messageID = String.valueOf(num);
        System.out.println("Message ID generated: <" + this.messageID + ">");
    }

    // Task Rubric Requirement validations
    public boolean checkMessageID() {
        return this.messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        return this.recipient.startsWith("+27") && this.recipient.length() == 13;
    }

    // Creates custom formatting payload using exact rubric logic breakdown
    public String createMessageHash() {
        String firstTwoId = this.messageID.substring(0, 2);

        // Parse individual words to isolate first and final references
        String[] words = this.messageContent.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0 ? words[words.length - 1] : "";

        // Clean off any lingering punctuation symbols for matching clean strings
        firstWord = firstWord.replaceAll("[^a-zA-Z]", "");
        lastWord = lastWord.replaceAll("[^a-zA-Z]", "");

        String combinedHash = firstTwoId + ":" + this.messageCount + ":" + firstWord + lastWord;
        return combinedHash.toUpperCase();
    }

    // Interactive confirmation workflow window tracking choice tasks
    public void SentMessage(Scanner input) {
        // Validate Content length constraints upfront
        if (this.messageContent.length() > 250) {
            System.out.println("Please enter a message of less than 250 characters.");
            return;
        } else {
            System.out.println("Message sent");
        }

        System.out.println("\nChoose message action choice:");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");
        System.out.print("Choice: ");
        String choice = input.nextLine().trim();

        if (choice.equals("1")) {
            System.out.println("Message successfully sent");
            sentMessages.add(this);
            totalGlobalMessages++;
            printMessages();
        } else if (choice.equals("2")) {
            System.out.println("Press 0 to delete the message");
            // Discard option selected; object omitted from saved registries.
        } else if (choice.equals("3")) {
            System.out.println("Message successfully stored");
            storeMessage(); // Runs JSON architecture generation routine
            totalGlobalMessages++;
        } else {
            System.out.println("Unknown command selection applied.");
        }
    }

    // Diagnostic console dump layout matching structural array specs precisely
    public void printMessages() {
        System.out.println("\n=== Message Summary Output ===");
        System.out.println("Message ID: " + this.messageID);
        System.out.println("Message Hash: " + createMessageHash());
        System.out.println("Recipient: " + this.recipient);
        System.out.println("Message: " + this.messageContent);
        System.out.println("================================\n");
    }

    public static int returnTotalMessagesText() {
        return totalGlobalMessages;
    }

    // Basic JSON File Writer serialization logic block
    public void storeMessage() {
        String jsonPayload = "{\n" +
                "  \"MessageID\": \"" + this.messageID + "\",\n" +
                "  \"MessageHash\": \"" + createMessageHash() + "\",\n" +
                "  \"Recipient\": \"" + this.recipient + "\",\n" +
                "  \"Message\": \"" + this.messageContent + "\"\n" +
                "}";

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(jsonPayload + "\n");
        } catch (IOException e) {
            System.out.println("Error processing storage: " + e.getMessage());
        }
    }
}