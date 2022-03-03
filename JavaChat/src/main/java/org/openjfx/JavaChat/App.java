package org.openjfx.JavaChat;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setScene(Scene setScene) throws IOException {
        stage.setScene(setScene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("Menu"), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        if (ChatController.connection != null)
            ChatController.connection.closeConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
