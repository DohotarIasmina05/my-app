package com.mycompany.app.Exceptions;

public class WrongRole extends Exception {

    public WrongRole() {
        super("The selected role is incorrect!");
    }
}
