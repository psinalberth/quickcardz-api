package com.github.psinalberth.quickcardz.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.github.psinalberth.quickcardz.model.vo.AuthenticationCredentialsDto;
import com.github.psinalberth.quickcardz.security.JwtTokenProvider;

@Service
public class AuthService {
	
	final JwtTokenProvider tokenProvider;
	final AuthenticationManager authenticationManager;
	
	public AuthService(final JwtTokenProvider tokenProvider, final AuthenticationManager authenticationManager) {
		this.tokenProvider = tokenProvider;
		this.authenticationManager = authenticationManager;
	}
	
	public Map<String, Object> signin(AuthenticationCredentialsDto credentials) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("username", credentials.getUsername());
		map.put("token", tokenProvider.generateToken(credentials.getUsername(), Collections.emptyList()));
		return map;
	}
	
	public void logout() {
		return;
	}
}