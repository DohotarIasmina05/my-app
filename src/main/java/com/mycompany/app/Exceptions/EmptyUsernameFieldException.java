package com.mycompany.app.Exceptions;

public class EmptyUsernameFieldException extends Exception {
    public EmptyUsernameFieldException() {
        super("The name field is empty. You need to complete it!");
    }
}
