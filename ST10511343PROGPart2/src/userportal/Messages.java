package userportal;



import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Messages {

    private String messageID;
    private int messageNumber;
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

    // Constructor
    public Messages(String messageID, int messageNumber,
                    String recipient, String message) {

        this.messageID = messageID;
        this.messageNumber = messageNumber;
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

    // Sends or manages a message
    public String sendMessage() {

        Scanner input = new Scanner(System.in);

        System.out.println("\nMessage Details");
        System.out.println("-------------------------");
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + message);

        System.out.println("\nChoose an option:");
        System.out.println("1. Send message");
        System.out.println("2. Disregard message");
        System.out.println("3. Save message for later");
        System.out.print("Enter your choice: ");

        int choice;

        try {
            choice = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            return "Invalid option.";
        }

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
                storedMessages.add(this);
                saveMessageToFile();

                return "Message saved for later.";

            default:
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
public static void startQuickChat() {

    Scanner input = new Scanner(System.in);

    int option = 0;
    int messageNumber = 0;

    System.out.println();
    System.out.println("================================");
    System.out.println("Welcome to QuickChat!");
    System.out.println("================================");

    while (option != 3) {

        System.out.println("\n1. Send Messages");
        System.out.println("2. Show recently sent messages");
        System.out.println("3. Quit");

        System.out.print("Enter your option: ");

        try {
            option = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            continue;
        }

        switch (option) {

            case 1:

                System.out.print(
                        "How many messages would you like to send? ");

                int numberOfMessages;

                try {
                    numberOfMessages =
                            Integer.parseInt(input.nextLine());

                    if (numberOfMessages <= 0) {
                        System.out.println(
                                "Please enter a number greater than zero.");
                        break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number.");
                    break;
                }

                for (int i = 0; i < numberOfMessages; i++) {

                    System.out.println(
                            "\nMessage " + (messageNumber + 1));

                    System.out.print(
                            "Enter recipient's cellphone number "
                            + "(e.g. +27821234567): ");

                    String recipient = input.nextLine();

                    if (!checkRecipientCell(recipient)) {
                        System.out.println(
                                "Invalid cellphone number. "
                                + "Please use the format +27821234567.");
                        continue;
                    }

                    System.out.print("Enter your message: ");

                    String message = input.nextLine();

                    if (message.trim().isEmpty()) {
                        System.out.println(
                                "Message cannot be empty.");
                        continue;
                    }

                    if (message.length() > 250) {
                        System.out.println(
                                "Message must not exceed 250 characters.");
                        continue;
                    }

                    messageNumber++;

                    Messages newMessage = new Messages(
                            generateMessageID(),
                            messageNumber,
                            recipient,
                            message
                    );

                    System.out.println(newMessage.sendMessage());
                }

                break;

            case 2:

                displaySentMessages();

                System.out.println(
                        "Total messages sent: "
                        + returnTotalMessages());

                break;

            case 3:

                System.out.println(
                        "Thank you for using QuickChat!");

                break;

            default:

                System.out.println(
                        "Invalid option. Please try again.");
        }
    }

    // Do not close this Scanner because System.in is also used
    // by the UserPortal class.
}

// Optional standalone entry point for testing Messages separately
public static void main(String[] args) {
    startQuickChat();
}

}
  
