package com.mycompany.app.Exceptions;

public class UsernameAlreadyExistsException extends Exception {
    private final String username;

    public UsernameAlreadyExistsException(String username) {
        super(String.format("An account with that username %s already exists.", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

