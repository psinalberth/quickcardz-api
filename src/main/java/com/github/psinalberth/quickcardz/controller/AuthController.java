package com.github.psinalberth.quickcardz.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.psinalberth.quickcardz.model.vo.AuthenticationCredentialsDto;
import com.github.psinalberth.quickcardz.service.AuthService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
	
	final AuthService service;	

	public AuthController(final AuthService service) {
		this.service = service;
	}
	
	@PostMapping(path = "/signin")
	public ResponseEntity<Map<String, Object>> signin(@Valid @RequestBody AuthenticationCredentialsDto credentials) {
		return ResponseEntity.ok(service.signin(credentials));
	}
	
	public ResponseEntity<Void> logout() {
		return ResponseEntity.noContent().build();
	}
}