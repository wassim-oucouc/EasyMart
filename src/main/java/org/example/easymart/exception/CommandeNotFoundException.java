package org.example.easymart.exception;

public class CommandeNotFoundException extends RuntimeException{

    public CommandeNotFoundException(String message)
    {
        super(message);
    }
}
