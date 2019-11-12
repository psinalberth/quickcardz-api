package com.github.psinalberth.quickcardz.model.mapper;

import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderMapper {
	
	@Named("encoder")
	public String encode(String rawPassword) {
		return new BCryptPasswordEncoder().encode(rawPassword);
	}
}
