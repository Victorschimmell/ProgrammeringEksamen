package org.openjfx.ProgEksamensProjekt;

import java.io.IOException;
import javafx.fxml.FXML;

public class KontaktController {

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }

}
