package com.v1lladev.naves.espaciales.dto.exceptions;

import java.io.IOException;

public class DateInvalidParameterException extends IOException {
    public DateInvalidParameterException(String message){
        super(message);
    };
}
