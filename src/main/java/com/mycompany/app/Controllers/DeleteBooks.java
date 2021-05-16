package com.mycompany.app.Controllers;
import com.mycompany.app.Exceptions.*;
import com.mycompany.app.Model.Book;
import com.mycompany.app.Model.LoggedInLibrarian;
import com.mycompany.app.Model.User;
import com.mycompany.app.Services.BookService;
import com.mycompany.app.Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class DeleteBooksController {

    @FXML
    private TextField deleteBooksField;
    @FXML
    private Text deleteBookMessage;
    @FXML
    private ListView listOfBooks;
    public void handleDeleteBookAction()
    {
        try {
            BookService.deleteBook(deleteBooksField.getText());
            deleteBookMessage.setText("Book deleted successfully.");
            listOfBooks.getItems().clear();
            setTheListOfBooks();
        }  catch (EmptyTitleFieldException e2)
        {
            deleteBookMessage.setText(e2.getMessage());
        }
        catch (WrongTitleException e1)
        {
            deleteBookMessage.setText(e1.getMessage());
        }

    }

    public void goBackToLibrarianView(ActionEvent goBackToLibrarianView) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LibrarianView.fxml"));
        Stage window = (Stage) ((Node) goBackToLibrarianView.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 600, 420));
        window.show();
    }
    public void setTheListOfBooks()
    {
        String library="";
        for(User user : UserService.userRepository.find())
        {
            if(user.getUsername().equals(LoggedInLibrarian.getUsername()))
            {
                library=user.getUsername();
            }
        }
        for(Book book : BookService.bookRepository.find())
        {

                listOfBooks.getItems().add(book.getName());
            }
        }
}
