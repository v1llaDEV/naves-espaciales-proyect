package com.v1lladev.naves.espaciales.dto.exceptions;

public class CustomInvalidParameterException extends RuntimeException{
    public CustomInvalidParameterException(String message){
        super(message);
    };
}
