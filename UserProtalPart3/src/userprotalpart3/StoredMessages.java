
package userprotalpart3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StoredMessages {

    private static final String FILE_NAME = "stored_messages.json";

    // Array containing all stored messages
    public static ArrayList<Messages> storedMessages = new ArrayList<>();

    // Save messages into the JSON file
    public static void saveMessagesToJSON() {

        JSONArray jsonArray = new JSONArray();

        for (Messages msg : storedMessages) {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("messageID", msg.getMessageID());
            jsonObject.put("messageNumber", msg.getMessageNumber());
            jsonObject.put("sender", msg.getSender());
            jsonObject.put("recipient", msg.getRecipient());
            jsonObject.put("message", msg.getMessage());
            jsonObject.put("messageHash", msg.getMessageHash());
            jsonObject.put("messageStatus", msg.getMessageStatus());

            jsonArray.add(jsonObject);
        }

        try (FileWriter writer = new FileWriter(FILE_NAME)) {

            writer.write(jsonArray.toJSONString());
            writer.flush();

            System.out.println("Stored messages saved to JSON.");

        } catch (IOException e) {

            System.out.println("Error saving JSON: " + e.getMessage());
        }
    }

    // Read messages from the JSON file
   
public static void loadMessagesFromJSON() {

    storedMessages.clear();

    java.io.File file = new java.io.File(FILE_NAME);

    // Check whether the JSON file exists
    if (!file.exists()) {
        System.out.println("No previously stored messages found.");
        return;
    }

    try (FileReader reader = new FileReader(file)) {

        JSONParser parser = new JSONParser();

        JSONArray jsonArray = (JSONArray) parser.parse(reader);

        for (Object object : jsonArray) {

            JSONObject jsonObject = (JSONObject) object;

            String id = (String) jsonObject.get("messageID");

            int number = ((Number)
                    jsonObject.get("messageNumber")).intValue();

            String sender = (String) jsonObject.get("sender");
            String recipient = (String) jsonObject.get("recipient");
            String message = (String) jsonObject.get("message");

            Messages msg = new Messages(
                    id, number, sender, recipient, message
            );

            msg.setMessageHash(
                    (String) jsonObject.get("messageHash")
            );

            msg.setMessageStatus(
                    (String) jsonObject.get("messageStatus")
            );

            storedMessages.add(msg);
        }

        System.out.println("Stored messages loaded successfully.");

    } catch (IOException | ParseException e) {

        System.out.println("Error loading stored messages: "
                + e.getMessage());
    }
}


    // Add a stored message and save it
    public static void addStoredMessage(Messages message) {

        storedMessages.add(message);
        saveMessagesToJSON();
    }

    // Display sender and recipient of all stored messages
    public static void displaySenderAndRecipient() {

        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages available.");
            return;
        }

        for (Messages msg : storedMessages) {

            System.out.println("Sender: " + msg.getSender());
            System.out.println("Recipient: " + msg.getRecipient());
            System.out.println("-------------------------");
        }
    }

    // Find the longest stored message
    public static void displayLongestStoredMessage() {

        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages available.");
            return;
        }

        Messages longest = storedMessages.get(0);

        for (Messages msg : storedMessages) {

            if (msg.getMessage().length()
                    > longest.getMessage().length()) {

                longest = msg;
            }
        }

        System.out.println("Longest stored message:");
        System.out.println(longest.getMessage());
    }

    // Search for a message using its ID
    public static void searchMessageID(String id) {

        for (Messages msg : storedMessages) {

            if (msg.getMessageID().equals(id)) {

                System.out.println("Message found:");
                System.out.println(msg.getMessage());
                System.out.println("Recipient: " + msg.getRecipient());
                return;
            }
        }

        System.out.println("Message ID not found.");
    }

    // Search messages using recipient cellphone number
    public static void searchByRecipient(String recipient) {

        boolean found = false;

        for (Messages msg : storedMessages) {

            if (msg.getRecipient().equals(recipient)) {

                System.out.println("Message: " + msg.getMessage());
                System.out.println("Message ID: " + msg.getMessageID());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No messages found for this recipient.");
        }
    }

    // Delete a stored message using its hash
    public static void deleteMessageByHash(String hash) {

        boolean removed = storedMessages.removeIf(
                msg -> msg.getMessageHash().equals(hash)
        );

        if (removed) {

            saveMessagesToJSON();
            System.out.println("Message successfully deleted.");

        } else {

            System.out.println("Message hash not found.");
        }
    }

    // Display all stored messages
    public static void displayStoredMessages() {

        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages available.");
            return;
        }

        for (Messages msg : storedMessages) {

            System.out.println("Message ID: " + msg.getMessageID());
            System.out.println("Sender: " + msg.getSender());
            System.out.println("Recipient: " + msg.getRecipient());
            System.out.println("Message: " + msg.getMessage());
            System.out.println("Message Hash: " + msg.getMessageHash());
            System.out.println("Status: " + msg.getMessageStatus());
            System.out.println("-------------------------");
        }
    }
}
