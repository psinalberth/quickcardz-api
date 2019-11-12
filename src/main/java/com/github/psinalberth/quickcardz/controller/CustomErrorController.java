package com.github.psinalberth.quickcardz.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

@RestController
@RequestMapping(value = "/error")
public class CustomErrorController implements ErrorController {	
	
	private final ErrorAttributes errorAttributes;
	
	public CustomErrorController(final ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}
	
	@GetMapping
	@PutMapping
	@PostMapping
	@DeleteMapping
	public ResponseEntity<Map<String, Object>> onError(HttpServletRequest request, HttpServletResponse response) {
	
		Map<String, Object> body = errorAttributes.getErrorAttributes(new ServletWebRequest(request), false);		
		return new ResponseEntity<>(body, HttpStatus.valueOf(response.getStatus()));
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}
}