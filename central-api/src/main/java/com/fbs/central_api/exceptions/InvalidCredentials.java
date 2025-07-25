package com.fbs.central_api.exceptions;

public class InvalidCredentials extends RuntimeException{

    public InvalidCredentials(String message){
        super(message);
    }
}
