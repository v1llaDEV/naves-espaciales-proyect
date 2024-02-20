package com.v1lladev.naves.espaciales.controllers;

import com.v1lladev.naves.espaciales.constants.ExceptionsMessageErrors;
import com.v1lladev.naves.espaciales.constants.StringConstants;
import com.v1lladev.naves.espaciales.dto.exceptions.CustomInvalidParameterException;
import com.v1lladev.naves.espaciales.dto.exceptions.GeneralResourceNotFoundException;
import com.v1lladev.naves.espaciales.dto.responses.ExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class GeneralErrorController {

    @ExceptionHandler(GeneralResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleCustomNotFoundParameterException(final GeneralResourceNotFoundException exception, final HttpServletRequest request) {
        return handleExceptionResponseDto(exception, request, HttpStatus.NOT_FOUND.value(), StringConstants.EMPTY_STRING);
    }

    @ExceptionHandler(CustomInvalidParameterException.class)
    public ResponseEntity<ExceptionResponseDto> handleCustomInvalidParameterException(final CustomInvalidParameterException exception, final HttpServletRequest request) {
        return handleExceptionResponseDto(exception, request, HttpStatus.BAD_REQUEST.value(), StringConstants.EMPTY_STRING);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionResponseDto> handleMissingServletRequestParameterException(final MissingServletRequestParameterException exception, final HttpServletRequest request) {
        return handleExceptionResponseDto(exception, request, HttpStatus.BAD_REQUEST.value(), StringConstants.EMPTY_STRING);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponseDto> HttpRequestMethodNotSupportedExceptionException(final HttpRequestMethodNotSupportedException exception, final HttpServletRequest request) {
        return handleExceptionResponseDto(exception, request, HttpStatus.METHOD_NOT_ALLOWED.value(), StringConstants.EMPTY_STRING);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleNoResourceFoundException(final NoResourceFoundException exception, final HttpServletRequest request) {
        return handleExceptionResponseDto(exception, request, HttpStatus.NOT_FOUND.value(), StringConstants.EMPTY_STRING);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponseDto> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException exception, final HttpServletRequest request) {
        return handleExceptionResponseDto(exception, request, BAD_REQUEST.value(), String.format(ExceptionsMessageErrors.VALIDATION_PARAMETER_ERROR, exception.getPropertyName(), exception.getValue()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleGeneralException(final Exception exception, final HttpServletRequest request) {
        return handleExceptionResponseDto(exception, request, INTERNAL_SERVER_ERROR.value(), "");
    }

    private ResponseEntity<ExceptionResponseDto> handleExceptionResponseDto(Exception exception, HttpServletRequest request, Integer httpStatus, String customMessage) {
        log.error(String.format("Exception -> CODE: %s - MESSAGE: %s - TIMESTAMP: %s - PATH: %s",
                httpStatus, exception.getMessage(), LocalDateTime.now(), request.getRequestURI()));
        return ResponseEntity
                .status(httpStatus)
                .body(ExceptionResponseDto.builder()
                        .status(httpStatus)
                        .error(customMessage.isBlank() ? exception.getMessage() : customMessage)
                        .path(request.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
