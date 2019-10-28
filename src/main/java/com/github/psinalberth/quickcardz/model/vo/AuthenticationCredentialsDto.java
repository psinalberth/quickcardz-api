package com.github.psinalberth.quickcardz.model.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AuthenticationCredentialsDto {
	
	@NotBlank(message = "Username cannot be empty.")
	@Size(min = 3, message = "Username must have at least {min} characters.")
	private String username;
	
	@NotBlank(message = "Password cannot be empty.")
	@Size(min = 8, message = "Password must have at least {min} digits.")
	private String password;
}
