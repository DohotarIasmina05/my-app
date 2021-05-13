package com.mycompany.app.Exceptions;

public class WrongTitleException extends Exception {
    public WrongTitleException() {
        super(String.format("The book does not exist in the database."));

    }
}
