package com.github.psinalberth.quickcardz.exception;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import lombok.Getter;

public class CustomMethodArgumentNotValidExceptionResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	private LocalDateTime timestamp;
	
	@Getter
	private Integer status;
	
	@Getter
	private String error;
	
	@Getter
	private String message;
	
	@Getter
	private String details;
	
	@Getter
	private Map<String, List<String>> errors;
	
	public CustomMethodArgumentNotValidExceptionResponse(LocalDateTime timestamp,  HttpStatus httpStatus, String details, List<FieldError> fieldErrors) {
		this.timestamp = timestamp;
		this.status = httpStatus.value();
		this.error = httpStatus.getReasonPhrase();
		this.message = "Validation failed for object.";
		this.details = details;
		this.errors = handleFieldErrors(fieldErrors);
	}

	private Map<String, List<String>> handleFieldErrors(List<FieldError> fieldErrors) {		
		return fieldErrors.stream().collect(
			Collectors.groupingBy(
				FieldError::getField, 
				Collectors.mapping(
					FieldError::getDefaultMessage, 
					Collectors.toList()
				)
			)
		);		
	}
}
