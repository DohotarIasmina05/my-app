package com.mycompany.exceptions;
public class IncorrectPassword extends Exception{
    public IncorrectPassword()
    {
        super("too few characters for password");
    }
}