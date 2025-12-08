package org.example.easymart.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> ClientNotFoundException(ClientNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CommandeNotFoundException.class)
    public ResponseEntity<?> CommandeNotFoundException(CommandeNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ConfirmationCommandeException.class)
    public ResponseEntity<?> ConfirmationCommandeException(ConfirmationCommandeException ex)
    {
        return ResponseEntity.status(HttpStatusCode.valueOf(422)).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalPaymentTypeException.class)
    public ResponseEntity<?> IllegalPaymentTypeException(IllegalPaymentTypeException ex)
    {
        return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(ex.getMessage());
    }

    @ExceptionHandler(InsufficientQuantityException.class)
    public ResponseEntity<?> InsufficientQuantityException(InsufficientQuantityException ex)
    {
        return ResponseEntity.status(HttpStatusCode.valueOf(422)).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> InvalidCredentialsException(InvalidCredentialsException ex)
    {
        return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(ex.getMessage());
    }

    @ExceptionHandler(ProduitNotFoundException.class)
    public ResponseEntity<?> ProduitNotFoundException(ProduitNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PromoCodeNotFoundException.class)
    public ResponseEntity<?> PromoCodeNotFoundException(PromoCodeNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> UserNotFoundException(UserNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> IllegalStateException(IllegalStateException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validationBody(MethodArgumentNotValidException ex)
    {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errors);
    }

    @ExceptionHandler(UserNotAllowed.class)
    public ResponseEntity<?> UserNotAllowed(UserNotAllowed ex)
    {
        return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotAuthenticated.class)
    public ResponseEntity<?> UserNotAuthenticated(UserNotAuthenticated ex)
    {
        return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(ex.getMessage());
    }











}
