package com.example.javafxproject0;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        showAuthPage();
    }

    public static void showAuthPage() throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(MainApplication.class.getResource("auth.fxml"))
        );
        primaryStage.setTitle("Login Page - Get Started");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showHomePage() throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(MainApplication.class.getResource("home.fxml"))
        );
        primaryStage.setTitle("Home Page - Welcome");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}