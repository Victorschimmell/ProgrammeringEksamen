package org.openjfx.JavaChat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController implements Initializable {
    public static NetworkConnection connection;

    public static String myName, otherName;

    DataPacket namepacket;

    @FXML
    private TextArea messagesArea = new TextArea();
    @FXML
    private TextField inputText = new TextField();

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        connection = KontaktController.isServer ? createServer() : createClient();

        try {
            if (ChatController.connection != null)
                ChatController.connection.startConnection();

            System.out.println("Connection succsessfull: ");
        } catch (Exception e) {
            System.out.println("Connection failed: ");
        }

    }

    @FXML
    private void sendMessage() {
        String message = MenuController.currentUser.getUsername() + " : ";
        message += inputText.getText();
        inputText.clear();

        messagesArea.appendText(message + "\n");

        DataPacket packet = new DataPacket(
                new Encryptor().enc(message.getBytes()));

        try {
            connection.send(packet);
        } catch (Exception e) {
            messagesArea.appendText("(Console): No one hears you :,(\n");
        }
    }

    private Server createServer() {
        return new Server(KontaktController.SelectedPort, data -> {
            DataPacket packet = (DataPacket) data;
            byte[] original = new Encryptor().dec(packet.getRawBytes());

            Platform.runLater(() -> {
                messagesArea.appendText(new String(original) + "\n");
            });
        });
    }

    private Client createClient() {
        return new Client("127.0.0.1", KontaktController.SelectedPort, data -> {
            DataPacket packet = (DataPacket) data;
            byte[] original = new Encryptor().dec(packet.getRawBytes());

            Platform.runLater(() -> {
                messagesArea.appendText(new String(original) + "\n");

            });
        });
    }

    @FXML
    private void returnpage() throws Exception {
        try {
            ChatController.connection.closeConnection();
        } catch (Exception e) {
        }
        App.setRoot("Kontakter");
    }
}
