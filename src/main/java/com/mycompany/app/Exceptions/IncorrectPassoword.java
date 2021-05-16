package com.mycompany.app.Exceptions;

public class IncorrectPassoword extends Exception {
    public IncorrectPassoword()
    {
        super("too few characters for password.");
    }
}

