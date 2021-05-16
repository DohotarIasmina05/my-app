package com.mycompany.app.Exceptions;

public class EmptyNameFieldException extends Exception {

    public EmptyNameFieldException() {
        super(String.format("The name field is empty. You need to complete it!"));
    }
}
