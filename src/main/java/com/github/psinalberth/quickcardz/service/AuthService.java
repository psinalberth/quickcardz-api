package com.github.psinalberth.quickcardz.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.github.psinalberth.quickcardz.model.entity.User;
import com.github.psinalberth.quickcardz.model.vo.AuthenticationCredentialsDto;
import com.github.psinalberth.quickcardz.repository.UserRepository;
import com.github.psinalberth.quickcardz.security.JwtTokenProvider;

@Service
public class AuthService {
	
	final JwtTokenProvider tokenProvider;
	final AuthenticationManager authenticationManager;
	final UserRepository userRepository;
	
	public AuthService(final JwtTokenProvider tokenProvider, final AuthenticationManager authenticationManager, final UserRepository userRepository) {
		this.tokenProvider = tokenProvider;
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
	}
	
	public Map<String, Object> signin(AuthenticationCredentialsDto credentials) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
		
		Optional<User> user = userRepository.findByUsername(credentials.getUsername());
		
		System.out.println(user);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("username", credentials.getUsername());
		map.put("token", tokenProvider.generateToken(credentials.getUsername(), Collections.emptyList()));
		return map;
	}
	
	public void logout() {
		return;
	}
}