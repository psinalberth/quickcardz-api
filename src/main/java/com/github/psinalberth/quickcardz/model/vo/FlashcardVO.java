package com.github.psinalberth.quickcardz.model.vo;

import lombok.Data;

@Data
public class FlashcardVO {
	
	private String term;
	private String definition;	
	private String imageUri;
	private String audioUri;
	private Long flashcardSetId;
}
