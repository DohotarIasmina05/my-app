package com.mycompany.app.Exceptions;

public class BooksDoesNotExistInLibrary extends Exception {

    public BooksDoesNotExistInLibrary() {
        super(String.format("The selected book does not exits in this library!"));
    }
}
