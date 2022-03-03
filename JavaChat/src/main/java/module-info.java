module org.openjfx.JavaChat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.openjfx.JavaChat to javafx.fxml;

    exports org.openjfx.JavaChat;
}
