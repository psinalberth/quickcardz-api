package com.github.psinalberth.quickcardz.model.vo;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CardSetDto {
	
	private Long id;
	
	@NotEmpty(message = "Cannot be empty.")
	private String title;
	
	private String description;
}
