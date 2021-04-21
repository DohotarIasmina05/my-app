package com.mycompany.exceptions;

public class InvalidLoginException extends Exception{

    public InvalidLoginException(){

        super(String.format("Incorect login.Please try again"));
    }
}