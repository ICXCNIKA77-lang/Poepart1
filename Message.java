import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Message {

    private final String recipient;
    private final String messageContent;
    private final String messageID;
    private final int messageCount;
    private String messageHash;
    private static int totalGlobalMessages = 0;

    public Message(String recipient, String messageContent, int count) {
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageCount = count;

        Random rand = new Random();
        long num = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        this.messageID = String.valueOf(num);
        this.messageHash = createMessageHash();
    }

    public String createMessageHash() {
        String firstTwoId = this.messageID.substring(0, Math.min(2, this.messageID.length()));
        String[] words = this.messageContent.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0].replaceAll("[^a-zA-Z]", "") : "";
        String lastWord = words.length > 0 ? words[words.length - 1].replaceAll("[^a-zA-Z]", "") : "";

        return (firstTwoId + ":" + this.messageCount + ":" + firstWord + lastWord).toUpperCase();
    }

    public void processMessageChoice(Scanner input, String loggedInUser) {
        if (this.messageContent.length() > 250) {
            System.out.println("Please enter a message of less than 250 characters.");
            return;
        }

        System.out.println("Message ID: " + this.messageID + " | Hash: " + this.messageHash);
        System.out.println("\nChoose message action choice:");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");
        System.out.print("Choice: ");
        String choice = input.nextLine().trim();

        if (choice.equals("1")) {
            System.out.println("Message successfully sent.");
            Poepart3.addMessageToParallelArrays(messageID, messageHash, recipient, messageContent, "Sent", loggedInUser);
            totalGlobalMessages++;
        } else if (choice.equals("2")) {
            System.out.println("Message Disregarded.");
            Poepart3.addMessageToParallelArrays(messageID, messageHash, recipient, messageContent, "Disregarded", loggedInUser);
            totalGlobalMessages++;
        } else if (choice.equals("3")) {
            System.out.println("Message successfully stored.");
            storeMessageToJson();
            Poepart3.addMessageToParallelArrays(messageID, messageHash, recipient, messageContent, "Stored", loggedInUser);
            totalGlobalMessages++;
        } else {
            System.out.println("Unknown command selection applied.");
        }
    }

    public static int returnTotalMessagesText() {
        return totalGlobalMessages;
    }

    public void storeMessageToJson() {
        String jsonPayload = "{\n" +
                "  \"MessageID\": \"" + this.messageID + "\",\n" +
                "  \"MessageHash\": \"" + this.messageHash + "\",\n" +
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