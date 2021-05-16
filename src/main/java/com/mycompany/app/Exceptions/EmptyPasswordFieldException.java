package com.mycompany.app.Exceptions;

public class EmptyPasswordFieldException extends Exception {
    public EmptyPasswordFieldException() {
        super("The password filed is empty. You need to complete it.");
    }
}
