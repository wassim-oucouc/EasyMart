package org.example.easymart.exception;

public class ProduitNotFoundException extends RuntimeException{

    public ProduitNotFoundException(String message)
    {
        super(message);
    }
}
