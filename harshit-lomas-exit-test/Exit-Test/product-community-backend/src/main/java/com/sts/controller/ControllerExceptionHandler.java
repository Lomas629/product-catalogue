package com.sts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sts.exceptions.DuplicateProductIDException;
import com.sts.exceptions.EmailAlreadyExist;
import com.sts.exceptions.InvalidCredentialsException;

@Component
@RestControllerAdvice
public class ControllerExceptionHandler {
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmailAlreadyExist.class)
    public String handleDuplicateEmail(EmailAlreadyExist ex){
        return ex.getMessage();
    }
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvalidCredentialsException.class)
    public String handleInvalidCredentials(InvalidCredentialsException ex){
        return ex.getMessage();
    }
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DuplicateProductIDException.class)
    public String handleDuplicateProductId(DuplicateProductIDException ex){
        return ex.getMessage();
    }
	
}
