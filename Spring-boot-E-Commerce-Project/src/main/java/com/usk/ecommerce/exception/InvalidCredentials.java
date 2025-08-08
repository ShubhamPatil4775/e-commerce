package com.usk.ecommerce.exception;

public class InvalidCredentials extends RuntimeException {
    public InvalidCredentials(String message){
        super(message);
    }
}
