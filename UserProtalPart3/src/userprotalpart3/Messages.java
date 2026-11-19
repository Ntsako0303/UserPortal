package userprotalpart3;



import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Messages {

    private String messageID;
    private int messageNumber;
    private String sender;
    private String recipient;
    private String message;
    private String messageHash;
    private String messageStatus;

    // Stores messages that have been sent
    public static ArrayList<Messages> sentMessages = new ArrayList<>();

    // Stores messages that have been disregarded
    private static ArrayList<Messages> disregardedMessages = new ArrayList<>();

    // Stores messages saved for later
    private static ArrayList<Messages> storedMessages = new ArrayList<>();

    // Keeps track of the total number of messages
    private static int totalMessages = 0;

    
// Original constructor
public Messages(String messageID, int messageNumber,
                String recipient, String message) {

    this(messageID, messageNumber, "Unknown sender",
            recipient, message);
}

// New constructor including sender
public Messages(String messageID, int messageNumber,
                String sender, String recipient, String message) {

    this.messageID = messageID;
    this.messageNumber = messageNumber;
    this.sender = sender;
    this.recipient = recipient;
    this.message = message;
    this.messageHash = createMessageHash();
    this.messageStatus = "Pending";
}


    // Generates a unique message ID
    public static String generateMessageID() {

        Random random = new Random();

        long id = 1000000000L
                + (long) (random.nextDouble() * 9000000000L);

        return String.valueOf(id);
    }

    // Checks whether the message ID is valid
    public boolean checkMessageID() {
        return messageID != null
                && messageID.length() <= 10;
    }

    // Checks whether the recipient's phone number is valid
    public static boolean checkRecipientCell(String recipient) {

        return recipient != null
                && recipient.matches("\\+\\d{1,3}\\d{9}");
    }

    // Creates a message hash
    public String createMessageHash() {

        String[] words = message.trim().split("\\s+");

        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0
                ? words[words.length - 1] : "";

        return (messageID.substring(0, 2)
                + ":" + messageNumber
                + ":" + firstWord
                + lastWord).toUpperCase();
    }

    
// Sends or manages a message when the choice is entered separately
public String sendMessage(int choice) {

    System.out.println("\nMessage Details");
    System.out.println("-------------------------");
    System.out.println("Message ID: " + messageID);
    System.out.println("Message Hash: " + messageHash);
    System.out.println("Recipient: " + recipient);
    System.out.println("Message: " + message);

    switch (choice) {

        case 1:
            messageStatus = "Sent";
            sentMessages.add(this);
            totalMessages++;
            return "Message successfully sent.";

        case 2:
            messageStatus = "Disregarded";
            disregardedMessages.add(this);
            return "Message disregarded.";

        case 3:
            messageStatus = "Stored";
            StoredMessages.addStoredMessage(this);
            return "Message saved for later.";

        default:
            return "Invalid option.";
    }
}

// Original method retained for compatibility
public String sendMessage() {

    Scanner input = new Scanner(System.in);

    System.out.println("1. Send message");
    System.out.println("2. Disregard message");
    System.out.println("3. Save message for later");
    System.out.print("Enter your choice: ");

    try {
        int choice = Integer.parseInt(input.nextLine());
        return sendMessage(choice);

    } catch (NumberFormatException e) {
        return "Invalid option.";
    }
}


    // Saves a message to a text file
    private void saveMessageToFile() {

        try (FileWriter writer =
                     new FileWriter("stored_messages.txt", true)) {

            writer.write("Message ID: " + messageID + "\n");
            writer.write("Message Number: " + messageNumber + "\n");
            writer.write("Recipient: " + recipient + "\n");
            writer.write("Message: " + message + "\n");
            writer.write("Message Hash: " + messageHash + "\n");
            writer.write("Status: " + messageStatus + "\n");
            writer.write("-------------------------\n");

        } catch (IOException e) {

            System.out.println("Error saving message: "
                    + e.getMessage());
        }
    }

    // Displays all sent messages
    public static void displaySentMessages() {

        if (sentMessages.isEmpty()) {
            System.out.println("No messages have been sent yet.");
            return;
        }

        System.out.println("\nRecently Sent Messages");
        System.out.println("-------------------------");

        for (Messages msg : sentMessages) {

            System.out.println("Message ID: " + msg.messageID);
            System.out.println("Recipient: " + msg.recipient);
            System.out.println("Message: " + msg.message);
            System.out.println("Message Hash: " + msg.messageHash);
            System.out.println("Status: " + msg.messageStatus);
            System.out.println("-------------------------");
        }
    }

    // Returns the total number of messages sent
    public static int returnTotalMessages() {
        return totalMessages;
    }

 
// Starts QuickChat after the user logs in
public static void startQuickChat(String senderDetails) {

    Scanner input = new Scanner(System.in);
    int option = 0;
    int messageNumber = 0;

    while (option != 4) {

        System.out.println("\n===== QUICKCHAT MENU =====");
        System.out.println("1. Send Messages");
        System.out.println("2. Show recently sent messages");
        System.out.println("3. Stored Messages");
        System.out.println("4. Quit");
        System.out.print("Enter your option: ");

        // Check that the user enters a number
        if (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            input.next();
            continue;
        }

        option = input.nextInt();
        input.nextLine(); // Clear the input line

        switch (option) {

            case 1:
                System.out.print("How many messages would you like to send? ");

                if (!input.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    input.next();
                    input.nextLine();
                    break;
                }

                int numberOfMessages = input.nextInt();
                input.nextLine();

                if (numberOfMessages <= 0) {
                    System.out.println("Please enter at least one message.");
                    break;
                }

                for (int i = 0; i < numberOfMessages; i++) {

                    System.out.println("\nMessage " + (i + 1));

                    System.out.print("Enter recipient cellphone number: ");
                    String recipient = input.nextLine();

                    // Validate recipient cellphone number
                    if (!recipient.matches("\\+\\d{1,3}\\d{9}")) {
                        System.out.println("Invalid cellphone number.");
                        System.out.println("Please use the correct format, for example +27123456789.");
                        i--;
                        continue;
                    }

                    System.out.print("Enter your message: ");
                    String message = input.nextLine();

                    if (message.trim().isEmpty()) {
                        System.out.println("Message cannot be empty.");
                        i--;
                        continue;
                    }

                    messageNumber++;

                    Messages newMessage = new Messages(
                            generateMessageID(),
                            messageNumber,
                            senderDetails,
                            recipient,
                            message
                    );

                    System.out.println("\nChoose what you want to do:");
                    System.out.println("1. Send Message");
                    System.out.println("2. Disregard Message");
                    System.out.println("3. Save Message for Later");
                    System.out.print("Enter your choice: ");

                    if (!input.hasNextInt()) {
                        System.out.println("Invalid choice. Message was not saved or sent.");
                        input.nextLine();
                        continue;
                    }

                    int messageChoice = input.nextInt();
                    input.nextLine();

                   System.out.println(newMessage.sendMessage(messageChoice));
                }

                break;

            case 2:
                displaySentMessages();
                System.out.println("Total messages sent: " + returnTotalMessages());
                break;

            case 3:
                StoredMessages.loadMessagesFromJSON();
                storedMessagesMenu(input);
                break;

            case 4:
                System.out.println("Thank you for using QuickChat. Goodbye!");
                break;

            default:
                System.out.println("Invalid option. Please choose between 1 and 4.");
                break;
        }
    }
}
// Stored Messages Submenu
public static void storedMessagesMenu(Scanner input) {

    int choice = 0;

    while (choice != 7) {

        System.out.println("1. Display sender and recipient");
System.out.println("2. Display longest stored message");
System.out.println("3. Search for a message ID");
System.out.println("4. Search messages by recipient");
System.out.println("5. Display all stored messages");
System.out.println("6. Delete a message using its hash");
System.out.println("7. Return to main menu");

        System.out.print("Enter your choice: ");

        try {
            choice = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            continue;
        }

        switch (choice) {

            case 1:
                StoredMessages.displaySenderAndRecipient();
                break;

            case 2:
                StoredMessages.displayLongestStoredMessage();
                break;

            case 3:
                System.out.print("Enter message ID: ");
                String id = input.nextLine();
                StoredMessages.searchMessageID(id);
                break;

            case 4:
                System.out.print("Enter recipient number: ");
                String recipient = input.nextLine();
                StoredMessages.searchByRecipient(recipient);
                break;

            case 5:
                StoredMessages.displayStoredMessages();
                break;

        case 6:
                System.out.print("Enter message hash: ");
                String hash = input.nextLine();
                StoredMessages.deleteMessageByHash(hash);
                break;

        case 7:
                System.out.println("Returning to main menu.");
                break;   
        }
    }
}

// Optional standalone entry point for testing Messages separately
public static void main(String[] args) {
    startQuickChat("Test User");
}
// Getter methods
public String getMessageID() {
    return messageID;
}

public int getMessageNumber() {
    return messageNumber;
}

public String getSender() {
    return sender;
}

public String getRecipient() {
    return recipient;
}

public String getMessage() {
    return message;
}

public String getMessageHash() {
    return messageHash;
}

public String getMessageStatus() {
    return messageStatus;
}

// Setter methods
public void setMessageHash(String messageHash) {
    this.messageHash = messageHash;
}

public void setMessageStatus(String messageStatus) {
    this.messageStatus = messageStatus;
}

}
  
