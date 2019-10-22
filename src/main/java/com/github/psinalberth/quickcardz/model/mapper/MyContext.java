package com.github.psinalberth.quickcardz.model.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import com.github.psinalberth.quickcardz.model.entity.Flashcard;
import com.github.psinalberth.quickcardz.model.entity.FlashcardSet;

@Component
public class MyContext {
	
	private FlashcardSet set;
	
	@BeforeMapping
	public void setEntity(@MappingTarget FlashcardSet set) {
		this.set = set;
	}
	
	@AfterMapping
	public void setParent(@MappingTarget Flashcard card) {
		card.setFlashcardSet(set);
	}
}
