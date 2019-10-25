package com.github.psinalberth.quickcardz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidRequestBodyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidRequestBodyException() {
		super();
	}
	
	public InvalidRequestBodyException(String message) {
		super(message);
	}
}
