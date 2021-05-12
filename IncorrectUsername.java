package com.mycompany.exceptions;

public class IncorrectUsername extends Exception{
    public IncorrectUsername()
    {
        super("too few characters for username");
    }
}