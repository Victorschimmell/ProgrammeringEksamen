module org.openjfx.ProgEksamensProjekt {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.openjfx.ProgEksamensProjekt to javafx.fxml;
    exports org.openjfx.ProgEksamensProjekt;
}
