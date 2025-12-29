package org.example.easymart.exception;

public class UserNotAllowed extends RuntimeException{
    public UserNotAllowed(String message)
    {
        super(message);
    }
}
