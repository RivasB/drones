package com.musala.drones.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = { DroneControlException.class })
    protected ResponseEntity<Object> handleConflict(DroneControlException exception, WebRequest request) {
        String bodyOfResponse = exception.getMessage();
        return handleExceptionInternal(exception, bodyOfResponse,
                new HttpHeaders(), exception.getHttpStatus(), request);
    }
}
