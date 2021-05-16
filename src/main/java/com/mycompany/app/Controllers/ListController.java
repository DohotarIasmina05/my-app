package com.mycompany.app.Controllers;

import Model.Book;
import Model.User;
import Services.UserService;
import com.mycompany.app.Exceptions.BooksDoesNotExistInLibrary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;

import java.io.IOException;
import java.util.Objects;

public class ListController {

    public static ObjectRepository<User> userRepository;
    public static ObjectRepository<Book> bookRepository;
    @FXML
    public ListView LibraryList;

    @FXML
    public Button libraryList;
    @FXML
    public Text showBooksOfLibraryMessage;

    public void Set() {
        for (User user : UserService.userRepository.find()) {
            if (Objects.equals(user.getRole(), "Admin")) {
                LibraryList.getItems().add(user.getUsername());
            }
        }
    }

    @FXML
    public void handleShowBooksOfLibrary(ActionEvent showBooksOfLibrary) throws IOException, BooksDoesNotExistInLibrary {



        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("BooksList.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        BooksOfLibraryController controller = loader.getController();

        Stage stage = (Stage) (libraryList.getScene().getWindow());
        stage.setTitle("Book list");
        stage.setScene(scene);
        stage.show();
    }


}
