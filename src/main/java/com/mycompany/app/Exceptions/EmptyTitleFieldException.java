package com.mycompany.app.Exceptions;


public class EmptyTitleFieldException extends Exception {

    public EmptyTitleFieldException() {
        super(String.format("The title field is empty. You need to complete it."));
    }
}
