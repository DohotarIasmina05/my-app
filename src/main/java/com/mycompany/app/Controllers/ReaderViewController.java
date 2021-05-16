package com.mycompany.app.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ReaderViewController {

    @FXML
    public Button listLibraries;

    @FXML
    public void handleGoBackToLogIn(ActionEvent goBackToLogIn) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) ((Node) goBackToLogIn.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 600, 350));
        window.show();
    }

    @FXML
    public void handleSeeListOfLibraries(ActionEvent seeListOfLibraries) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("BooksList.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        Stage stage = (Stage) (listLibraries.getScene().getWindow());
        stage.setTitle("Books list");
        stage.setScene(scene);
        stage.show();
    }
}