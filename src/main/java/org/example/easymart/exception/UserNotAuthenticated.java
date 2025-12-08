package org.example.easymart.exception;

public class UserNotAuthenticated extends RuntimeException{

    public UserNotAuthenticated(String message)
    {
        super(message);
    }
}
