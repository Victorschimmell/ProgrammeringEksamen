package org.openjfx.ProgEksamensProjekt;

import java.io.IOException;
import javafx.fxml.FXML;

public class MenuController {

    User u;

    @FXML
    private void switchToKontakter() throws IOException {
        u = new User(1, "navn", "Kode", "[1,2,3,4,5,6,7,8,9]");
        u.getPassword();
        u.getFriends();

        App.setRoot("Kontakter");

    }
}
