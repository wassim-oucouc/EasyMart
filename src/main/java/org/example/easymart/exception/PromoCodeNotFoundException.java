package org.example.easymart.exception;

public class PromoCodeNotFoundException extends RuntimeException{

    public PromoCodeNotFoundException(String message)
    {
        super(message);
    }
}
