package com.example.hello_java.exception;

public class UserNotFoundException extends RuntimeException {
    // private static final long serialVersionUID = 1L;

    // public UserNotFoundException(String id) {
    //     super("Could not find user " + id);
    // }
    public UserNotFoundException(String message) {
        super(message);
    }
}