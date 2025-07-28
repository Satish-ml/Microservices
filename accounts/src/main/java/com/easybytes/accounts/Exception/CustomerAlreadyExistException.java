package com.easybytes.accounts.Exception;

public class CustomerAlreadyExistException extends RuntimeException {

    public CustomerAlreadyExistException(String message){
        super(message);
    }
}
