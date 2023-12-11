package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 458, 553);
        stage.setTitle("Chat Room");
        stage.setResizable(false);
        stage.setScene(scene);

        // Get the controller instance and pass it to the Client constructor
        Controller controller = fxmlLoader.getController();
        Client cl = new Client(controller);
        controller.setClient(cl);

        // Set up the stage close event to properly shut down the client
        stage.setOnCloseRequest(windowEvent -> cl.shutdown());

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
