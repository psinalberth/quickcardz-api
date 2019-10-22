package com.github.psinalberth.quickcardz.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class CustomExceptionResponse implements Serializable {

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
	
	public CustomExceptionResponse(LocalDateTime timestamp,  HttpStatus httpStatus,  String message, String details) {
		this.timestamp = timestamp;
		this.status = httpStatus.value();
		this.error = httpStatus.getReasonPhrase();
		this.message = message;
		this.details = details;
	}	
}