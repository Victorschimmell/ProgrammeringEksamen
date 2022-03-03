package org.openjfx.JavaChat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class KontaktController implements Initializable {
    public static boolean isServer = false;
    public static int SelectedPort = 5555;

    @FXML
    private Button StartChat;
    @FXML
    private CheckBox ServerSelected;
    @FXML
    private TextField PortNR;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        PortNR.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    PortNR.setText(newValue.replaceAll("[^\\d]", ""));
                } else {
                    try {
                        SelectedPort = Integer.parseInt(PortNR.getText());
                    } catch (Exception e) {
                        SelectedPort = 0;
                    }
                }
            }
        });

    }

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }

    @FXML
    private void toggleConnection() throws IOException {
        if (ServerSelected.isSelected()) {
            isServer = true;
            StartChat.setText("Host your chatroom");
        } else {
            isServer = false;
            StartChat.setText("Connect to chatroom");
        }

    }

    @FXML
    private void StartChat() throws IOException {

        isServer = ServerSelected.isSelected();

        try {
            SelectedPort = Integer.parseInt(PortNR.getText());
        } catch (Exception e) {
            SelectedPort = 0;
        }
        
        App.setRoot("ChatRoom");

    }

}
