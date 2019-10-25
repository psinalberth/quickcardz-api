package com.github.psinalberth.quickcardz.exception.handler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.github.psinalberth.quickcardz.exception.CustomExceptionResponse;
import com.github.psinalberth.quickcardz.exception.CustomMethodArgumentNotValidExceptionResponse;
import com.github.psinalberth.quickcardz.exception.ResourceNotFoundException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler {
	
	final MessageSource messageSource;
	
	@Autowired
	public CustomResponseEntityExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public final ResponseEntity<CustomExceptionResponse> handleResourceNotFound(Exception exception, WebRequest request) {
		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND , exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<CustomMethodArgumentNotValidExceptionResponse> handleBadRequestException(MethodArgumentNotValidException  exception, WebRequest request) {
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		CustomMethodArgumentNotValidExceptionResponse exceptionResponse = new CustomMethodArgumentNotValidExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST, request.getDescription(false), fieldErrors);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
