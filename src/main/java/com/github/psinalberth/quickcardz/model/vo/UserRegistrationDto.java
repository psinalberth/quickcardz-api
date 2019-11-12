package com.github.psinalberth.quickcardz.model.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRegistrationDto {
	
	@NotEmpty(message = "Full name cannot be empty.")
	private String fullName;
	
	@NotEmpty(message = "Username cannot be empty.")
	@Size(min = 3, message = "User must have at leat {min} digits")
	private String username;
	
	@NotEmpty(message = "Password cannot be empty.")
	@Size(min = 8, message = "Password must have at leat {min} digits")
	private String password;
}
