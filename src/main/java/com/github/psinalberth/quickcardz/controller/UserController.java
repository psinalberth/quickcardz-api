package com.github.psinalberth.quickcardz.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.psinalberth.quickcardz.model.vo.UserRegistrationDto;
import com.github.psinalberth.quickcardz.service.UserService;

@RestController
@RequestMapping(path = "/register")
public class UserController {
	
	private final UserService service;
	
	public UserController(final UserService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<UserRegistrationDto> save(@Valid @RequestBody UserRegistrationDto user) {
		return new ResponseEntity<>(service.save(user), HttpStatus.CREATED);
	}
}