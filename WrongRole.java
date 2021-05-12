package com.mycompany.exceptions;

public class WrongRoleException extends Exception {

    public WrongRoleException() {
        super(String.format("The selected role is incorrect"));
    }
}