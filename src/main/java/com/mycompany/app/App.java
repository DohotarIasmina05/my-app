package com.mycompany.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.File;
import java.net.URL;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        URL url = new File("src/main/java/com/mycompany/app/login.fxml").toURI().toURL();
       Parent root = FXMLLoader.load(url);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 520, 280));


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
