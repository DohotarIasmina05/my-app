package com.mycompany.app.Exceptions;

public class IncorrectUsername extends Exception {
    public IncorrectUsername()
    {
        super("too few characters for username!");
    }
}
