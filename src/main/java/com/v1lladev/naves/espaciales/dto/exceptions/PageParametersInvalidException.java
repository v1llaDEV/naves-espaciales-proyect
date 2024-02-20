package com.v1lladev.naves.espaciales.dto.exceptions;

import java.io.IOException;

public class PageParametersInvalidException extends IOException {
    public PageParametersInvalidException(String message){
        super(message);
    };
}
