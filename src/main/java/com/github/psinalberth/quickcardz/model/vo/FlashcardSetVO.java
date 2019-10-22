package com.github.psinalberth.quickcardz.model.vo;

import java.util.List;

import lombok.Data;

@Data
public class FlashcardSetVO {
	
	private Long flashcardSetId;
	private String title;	
	private String description;	
	private List<FlashcardVO> flashcards;
}
