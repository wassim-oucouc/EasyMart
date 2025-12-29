package org.example.easymart.exception;

public class IllegalPaymentTypeException extends RuntimeException{

    public IllegalPaymentTypeException(String message)
    {
        super(message);
    }
}
