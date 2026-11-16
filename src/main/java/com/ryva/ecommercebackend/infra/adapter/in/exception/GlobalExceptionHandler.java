package com.ryva.ecommercebackend.infra.adapter.in.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({RuntimeException.class})
    public ErrorResponse handleInvalidException(RuntimeException e){
        return new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT.value());
    }
}
