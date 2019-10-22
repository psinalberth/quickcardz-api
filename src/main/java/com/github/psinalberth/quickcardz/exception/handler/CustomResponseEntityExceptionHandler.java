package com.github.psinalberth.quickcardz.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.psinalberth.quickcardz.exception.CustomExceptionResponse;
import com.github.psinalberth.quickcardz.exception.ResourceNotFoundException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public final ResponseEntity<CustomExceptionResponse> handleResourceNotFound(Exception exception, WebRequest request) {
		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND , exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
}
