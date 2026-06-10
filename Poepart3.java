import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Poepart3 {

    // Parallel Arrays
    static ArrayList<String> sentMessages = new ArrayList<>();
    static ArrayList<String> disregardedMessages = new ArrayList<>();
    static ArrayList<String> storedMessages = new ArrayList<>();
    static ArrayList<String> messageHashArray = new ArrayList<>();
    static ArrayList<String> messageIDArray = new ArrayList<>();
    static ArrayList<String> recipientArray = new ArrayList<>();
    static ArrayList<String> senderArray = new ArrayList<>();

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println(" User Registration ");

            System.out.print("Enter first name: ");
            String firstName = input.nextLine();
            System.out.print("Enter last name: ");
            String lastName = input.nextLine().trim();

            System.out.println("(Username must contain an underscore (_) and be 5 characters or less)");
            System.out.print("Enter username: ");
            String username = input.nextLine().trim();

            System.out.println("(Password must be 8+ chars, have a capital letter, a number, and a special character)");
            System.out.print("Enter password: ");
            String password = input.nextLine().trim();

            System.out.println("(Start with +27 and be 13 characters total)");
            System.out.print("Enter cellphone number: ");
            String cellphone = input.nextLine().trim();

            User currentUser = new User(firstName, lastName, username, password, cellphone);
            String registerResult = currentUser.registerUser();
            System.out.println("\n" + registerResult);

            if (registerResult.equals("The user has successfully registered")) {
                System.out.println("\n User Login ");
                System.out.print("Enter username to login: ");
                String loginUsername = input.nextLine();
                System.out.print("Enter password to login: ");
                String loginPassword = input.nextLine();

                String loginResult = currentUser.returnLoginStatus(loginUsername, loginPassword);
                System.out.println(loginResult);

                if (currentUser.loginUser(loginUsername, loginPassword)) {
                    // Load any stored messages from JSON before starting
                    loadStoredMessagesFromJSON(currentUser.getUserName());

                    boolean running = true;
                    while (running) {
                        System.out.println("\n- QuickChat Main Menu -");
                        System.out.println("1. Add New Message");
                        System.out.println("2. Display All Sent Messages");
                        System.out.println("3. Display Longest Sent Message");
                        System.out.println("4. Search Message by ID");
                        System.out.println("5. Search Messages by Recipient");
                        System.out.println("6. Delete Message by Hash");
                        System.out.println("7. Display Sent Messages Report");
                        System.out.println("0. Exit");
                        System.out.print("Enter your choice: ");

                        String choice = input.nextLine().trim();

                        switch (choice) {
                            case "1":
                                System.out.print("Enter recipient cellphone number: ");
                                String recipientCell = input.nextLine().trim();
                                System.out.print("Enter message content: ");
                                String msgText = input.nextLine().trim();

                                Message msg = new Message(recipientCell, msgText, Message.returnTotalMessagesText() + 1);
                                msg.processMessageChoice(input, currentUser.getUserName());
                                break;
                            case "2":
                                System.out.println("\n--- All Sent/Stored Messages ---");
                                System.out.println(displaySenderAndRecipientStored());
                                break;
                            case "3":
                                System.out.println("\n--- Longest Message ---");
                                System.out.println(displayLongestStoredMessage());
                                break;
                            case "4":
                                System.out.print("Enter Message ID to search: ");
                                System.out.println(searchMessageByID(input.nextLine().trim()));
                                break;
                            case "5":
                                System.out.print("Enter Recipient to search: ");
                                System.out.println(searchMessagesByRecipient(input.nextLine().trim()));
                                break;
                            case "6":
                                System.out.print("Enter Message Hash to delete: ");
                                System.out.println(deleteMessageByHash(input.nextLine().trim()));
                                break;
                            case "7":
                                System.out.println(displayFullReport());
                                break;
                            case "0":
                                System.out.println("Exiting QuickChat. Total messages processed: " + Message.returnTotalMessagesText());
                                running = false;
                                break;
                            default:
                                System.out.println("Invalid selection. Please try again.");
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void addMessageToParallelArrays(String id, String hash, String recipient, String content, String status, String sender) {
        messageIDArray.add(id);
        messageHashArray.add(hash);
        recipientArray.add(recipient);
        senderArray.add(sender);

        if (status.equalsIgnoreCase("Sent")) {
            sentMessages.add(content);
            disregardedMessages.add("");
            storedMessages.add("");
        } else if (status.equalsIgnoreCase("Disregarded")) {
            sentMessages.add("");
            disregardedMessages.add(content);
            storedMessages.add("");
        } else if (status.equalsIgnoreCase("Stored")) {
            sentMessages.add("");
            disregardedMessages.add("");
            storedMessages.add(content);
        }
    }

    public static String displaySenderAndRecipientStored() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < messageIDArray.size(); i++) {
            String msg = getActiveMessageContent(i);
            if (!msg.isEmpty() && disregardedMessages.get(i).isEmpty()) {
                sb.append("Sender: ").append(senderArray.get(i))
                        .append(" | Recipient: ").append(recipientArray.get(i))
                        .append(" | Message: \"").append(msg).append("\"\n");
            }
        }
        return sb.length() > 0 ? sb.toString() : "No messages found.";
    }

    public static String displayLongestStoredMessage() {
        String longest = "";
        for (int i = 0; i < messageIDArray.size(); i++) {
            String msg = getActiveMessageContent(i);
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest.isEmpty() ? "No messages exist." : longest;
    }

    public static String searchMessageByID(String id) {
        for (int i = 0; i < messageIDArray.size(); i++) {
            if (messageIDArray.get(i).equals(id)) {
                return "\"" + getActiveMessageContent(i) + "\"";
            }
        }
        return "Message ID not found.";
    }

    public static String searchMessagesByRecipient(String recipient) {
        StringBuilder results = new StringBuilder();
        for (int i = 0; i < recipientArray.size(); i++) {
            if (recipientArray.get(i).equals(recipient)) {
                String content = getActiveMessageContent(i);
                if (!content.isEmpty()) {
                    results.append("\"").append(content).append("\"\n");
                }
            }
        }
        return results.length() > 0 ? results.toString().trim() : "No messages found for this recipient.";
    }

    public static String deleteMessageByHash(String hash) {
        for (int i = 0; i < messageHashArray.size(); i++) {
            if (messageHashArray.get(i).equalsIgnoreCase(hash)) {
                String content = getActiveMessageContent(i);

                messageIDArray.remove(i);
                messageHashArray.remove(i);
                recipientArray.remove(i);
                senderArray.remove(i);
                sentMessages.remove(i);
                disregardedMessages.remove(i);
                storedMessages.remove(i);

                return "Message: \"" + content + "\" successfully deleted.";
            }
        }
        return "Hash not found.";
    }

    public static String displayFullReport() {
        StringBuilder report = new StringBuilder("=== System Message Report ===\n");
        for (int i = 0; i < messageIDArray.size(); i++) {
            if (disregardedMessages.get(i).isEmpty()) {
                report.append("Message Hash: ").append(messageHashArray.get(i)).append("\n")
                        .append("Recipient: ").append(recipientArray.get(i)).append("\n")
                        .append("Message: ").append(getActiveMessageContent(i)).append("\n")
                        .append("----------------------------\n");
            }
        }
        return report.toString();
    }

    public static String getActiveMessageContent(int index) {
        if (!sentMessages.get(index).isEmpty()) return sentMessages.get(index);
        if (!storedMessages.get(index).isEmpty()) return storedMessages.get(index);
        if (!disregardedMessages.get(index).isEmpty()) return disregardedMessages.get(index);
        return "";
    }

    public static void loadStoredMessagesFromJSON(String username) {
        File file = new File("messages.json");
        if (!file.exists()) return;

        try (Scanner fileScanner = new Scanner(file)) {
            String currentID = "", currentHash = "", currentRecipient = "", currentMsg = "";
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.contains("\"MessageID\"")) currentID = extractJsonValue(line);
                else if (line.contains("\"MessageHash\"")) currentHash = extractJsonValue(line);
                else if (line.contains("\"Recipient\"")) currentRecipient = extractJsonValue(line);
                else if (line.contains("\"Message\"")) {
                    currentMsg = extractJsonValue(line);
                    addMessageToParallelArrays(currentID, currentHash, currentRecipient, currentMsg, "Stored", username);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Warning: Could not read messages.json");
        }
    }

    private static String extractJsonValue(String line) {
        int colonIndex = line.indexOf(':');
        if (colonIndex == -1) return "";
        return line.substring(colonIndex + 1).trim().replaceAll("^\"|\"$|,$|^\",$", "").replace("\"", "");
    }
}