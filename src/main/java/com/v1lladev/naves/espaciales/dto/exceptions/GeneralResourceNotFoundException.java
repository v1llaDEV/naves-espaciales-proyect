package com.v1lladev.naves.espaciales.dto.exceptions;

public class GeneralResourceNotFoundException extends RuntimeException{
    public GeneralResourceNotFoundException(String message){
        super(message);
    };
}
