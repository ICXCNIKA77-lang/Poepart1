import java.util.Scanner;

/**
 *
 * @author given
 */
public class Poepart1 {
    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            System.out.println(" User Registration");

            System.out.print("Enter first name: ");
            String firstName = input.nextLine();
            System.out.print("Enter last name: ");
            String lastName = input.nextLine().trim();

            //for Username
            System.out.println("\n[(Username must contain an underscore (_) and be 5 characters or less)]");
            System.out.print("Enter username: ");
            String username = input.nextLine().trim();

            //for Password
            System.out.println("\n[(Password must be 8+ chars, have a capital letter, a number, and a special character)]");
            System.out.print("Enter password: ");
            String password = input.nextLine().trim();

            //for Cellphone
            System.out.println("\n[(Start with +27 and be 13 characters totaL)]");
            System.out.print("Enter cellphone number: ");
            String cellphone = input.nextLine().trim();

            Login reg = new Login(firstName, lastName, username, password, cellphone);

            // Call the registration method
            String registerResult = reg.registerUser();
            System.out.println(registerResult);

            // Check if registration was successful
            if (registerResult.equals("The user has successfully registered")) {
                System.out.println("User Login");
                System.out.print("Enter username to login: ");
                String loginUsername = input.nextLine();
                System.out.print("Enter password to login: ");
                String loginPassword = input.nextLine();

                String loginResult = reg.returnLoginStatus(loginUsername, loginPassword);
                System.out.println(loginResult);


                // Only run Part 2 features if login succeeded
                if (reg.loginUser(loginUsername, loginPassword)) {
                    System.out.println("\nWelcome to QuickChat.");

                    boolean running = true;
                    // While loop tracks application state until user explicitly chooses to quit
                    while (running) {
                        System.out.println("\nPlease choose one of the following features from a numeric menu:");
                        System.out.println("1) Option 1) Send Messages");
                        System.out.println("2) Option 2) Show recently sent messages");
                        System.out.println("3) Option 3) Quit");
                        System.out.print("Enter your choice: ");

                        String choice = input.nextLine().trim();

                        switch (choice) {
                            case "1":
                                System.out.print("How many messages do you wish to enter? ");
                                int totalToEnter = Integer.parseInt(input.nextLine().trim());

                                // For loop captures the specific allocation quota assigned by the user
                                for (int i = 0; i < totalToEnter; i++) {
                                    System.out.println("\n--- Entering Details for Message " + (i + 1) + " ---");
                                    System.out.print("Enter recipient cellphone number: ");
                                    String recipientCell = input.nextLine().trim();

                                    System.out.print("Enter message content: ");
                                    String msgText = input.nextLine().trim();

                                    // Instantiate the message class object
                                    messaging msg = new messaging(recipientCell, msgText, messaging.returnTotalMessagesText());

                                    // Process validations and message workflow options
                                    msg.SentMessage(input);
                                }
                                break;

                            case "2":
                                // Displays the feature state requirement
                                System.out.println("\nComing Soon.");
                                break;

                            case "3":
                                // Display execution aggregates on close out
                                System.out.println("\nTotal number of messages processed: " + messaging.returnTotalMessagesText());
                                running = false;
                                break;

                            default:
                                System.out.println("Invalid selection. Please try again.");
                                break;
                        }
                    }
                }
                // --- POE PART 2 INTEGRATION ENDS HERE ---
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}