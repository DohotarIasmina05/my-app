package com.mycompany.app.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ComboBox  comb;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile= new File("images/book_image.png");
        Image brandingImage= new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
        ObservableList<String> list ;
        list =  FXCollections.observableArrayList("Admin","Reader");
        comb.setItems( list);


    }
    public void Select(ActionEvent event){
        String s= comb.getSelectionModel().getSelectedItem().toString();

    }
    public void loginButtonOnAction(ActionEvent event){


        loginMessageLabel.setText("Please enter username and password");

    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}
