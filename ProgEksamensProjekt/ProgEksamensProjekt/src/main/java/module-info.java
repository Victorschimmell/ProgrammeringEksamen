module org.openjfx.ProgEksamensProjekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    

    opens org.openjfx.ProgEksamensProjekt to javafx.fxml;
    exports org.openjfx.ProgEksamensProjekt;
}
