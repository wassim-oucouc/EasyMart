package org.example.easymart.exception;

public class IllegalPaymentType extends RuntimeException{

    public IllegalPaymentType(String message)
    {
        super(message);
    }
}
