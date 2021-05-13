package com.mycompany.app.Services;

import com.mycompany.app.Exceptions.*;
import com.mycompany.app.Model.Book;
import com.mycompany.app.Model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.Objects;

import static com.mycompany.app.Services.FileSystemSerivce.getPathToFile;

public class BookService {

    public static ObjectRepository<Book> bookRepository;
    public static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        try (Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("BooksDataBase.db").toFile())
                .openOrCreate("test", "test")) {

            bookRepository = database.getRepository(Book.class);
        }
    }

    public static void addBook(String title, String author, String genre, String libraryName) throws EmptyTitleFieldException, EmptyAuthorFieldException{
        checkEmptyFields(title, author, genre, libraryName);
        String borrowedDate = "";
        String returnedDate = "";
        String username = "";
        bookRepository.insert(new Book(title, author,borrowedDate, returnedDate, username));
    }

    private static void checkEmptyFields(String title, String author, String genre, String libraryName) throws EmptyTitleFieldException, EmptyAuthorFieldException {
        if (title == "") {
            throw new EmptyTitleFieldException();
        } else {
            if (author == "") {
                throw new EmptyAuthorFieldException();
            }
        }
    }

    public static void checkBookExistInLibrary(String bookName) throws BooksDoesNotExistInLibrary {

        int sw = 0;
        for (Book book : bookRepository.find()) {
            if (Objects.equals(book.getName(), bookName)) {
                sw = 1;
            }
        }

        if (sw == 0) {
            throw new BooksDoesNotExistInLibrary();
        }
    }
    public static void deleteBook(String title) throws WrongTitleException, EmptyTitleFieldException
    {
        int sw=0;
        Book aux=new Book();
        if(title=="")
            throw new EmptyTitleFieldException();
        for(Book book : bookRepository.find())
        {
            if(title.equals(book.getName())) {
                aux = book;
                sw=1;
            }
        }
        if(sw==0) {
            throw new WrongTitleException();
        }
        bookRepository.remove(aux);
    }


    private static void checkBooksExistInLibrary() throws BooksDoesNotExistInLibrary {

        int sw = 0;
        for (Book book : bookRepository.find()) {
            if ( Objects.equals(book.getUserName(), "")) {
                sw = 1;
                break;
            }
        }
        if (sw == 0) {
            throw new BooksDoesNotExistInLibrary();
        }
    }


}