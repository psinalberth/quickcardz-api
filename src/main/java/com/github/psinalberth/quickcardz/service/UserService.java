package com.github.psinalberth.quickcardz.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.psinalberth.quickcardz.model.entity.User;
import com.github.psinalberth.quickcardz.model.mapper.UserMapper;
import com.github.psinalberth.quickcardz.model.vo.UserRegistrationDto;
import com.github.psinalberth.quickcardz.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private final UserRepository repository;
	private final UserMapper mapper;
	
	public UserService(final UserRepository repository, final UserMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public UserRegistrationDto save(UserRegistrationDto dto) {
		User user = mapper.fromDtoToEntity(dto);
		user = repository.save(user);
		return mapper.fromEntityToDto(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {		
		String msg = String.format("Username %s not found.", username);		
		return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(msg));		
	}
}