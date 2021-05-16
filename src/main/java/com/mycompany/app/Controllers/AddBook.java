package com.mycompany.app.Controllers;

import com.mycompany.app.Exceptions.EmptyAuthorFieldException;
import com.mycompany.app.Exceptions.EmptyTitleFieldException;
import Services.BookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddBook {

    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField genreField;
    @FXML
    private TextField libraryNameField;
    @FXML
    private Button addButton;
    @FXML
    private Text addBookMessage;

    public void handleAddBookAction() {
        try {
            BookService.addBook(titleField.getText(), authorField.getText());
            addBookMessage.setText("Book added successfully!");
        } catch (EmptyTitleFieldException e1) {
            addBookMessage.setText(e1.getMessage());
        } catch (EmptyAuthorFieldException e2) {
            addBookMessage.setText(e2.getMessage());
        }
    }

    public void goBackToLibrarianView(ActionEvent goBackToLibrarianView) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LibrarianView.fxml"));
        Stage window = (Stage) ((Node) goBackToLibrarianView.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 600, 350));
        window.show();
    }
}