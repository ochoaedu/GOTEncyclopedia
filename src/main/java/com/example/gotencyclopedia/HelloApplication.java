package com.example.gotencyclopedia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        stage.setTitle("GOT Encyclopedia");
        //asigining the icon to the top corner
        stage.getIcons().add(new Image("file:///C:\\Users\\edude\\OneDrive\\Documents\\Georgian College Projects\\GOTEncyclopedia\\stark_icon.png")); // Establecer el ícono para el Stage
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}