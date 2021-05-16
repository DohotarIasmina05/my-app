package com.mycompany.app.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LibrarianViewController {

    public void handleGoBackToLogIn(ActionEvent goBackToLogIn) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) ((Node) goBackToLogIn.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 600, 350));
        window.show();
    }
}