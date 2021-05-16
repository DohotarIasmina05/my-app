package com.mycompany.app.Controllers;


import com.mycompany.app.Exceptions.*;
import com.mycompany.app.Model.Book;
import com.mycompany.app.Services.BookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.dizitart.no2.objects.ObjectRepository;
import java.util.Objects;
public class BooksOfLibraryController {

    public static ObjectRepository<Book> bookRepository;

    @FXML
    public TextField bookName;
    @FXML
    public ListView bookList;

    @FXML
    public Text authorMessage;

    @FXML
    public Text errorMessage;

    public void handleShowInformationOfBook(ActionEvent showInformationOfBook) {

        authorMessage.setText("");

        errorMessage.setText("");

        try {
            BookService.checkBookExistInLibrary(bookName.getText());
            for (Book book : BookService.bookRepository.find()) {
                if (Objects.equals(book.getName(), bookName.getText())) {
                    authorMessage.setText("The author of the book is " + book.getAuthor());

                }
            }
        } catch ( BooksDoesNotExistInLibrary e1) {
            errorMessage.setText(e1.getMessage());
        }

    }

    public void handleBorrowBook(ActionEvent borrowBook) {

        authorMessage.setText("");

        errorMessage.setText("");


    }


}