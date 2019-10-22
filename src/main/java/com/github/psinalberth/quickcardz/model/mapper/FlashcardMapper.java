package com.github.psinalberth.quickcardz.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.psinalberth.quickcardz.model.entity.Flashcard;
import com.github.psinalberth.quickcardz.model.vo.FlashcardDto;

@Mapper
public interface FlashcardMapper extends IAbstractMapper<Flashcard, FlashcardDto> {
	
	@Mapping(source = "cardSetId", target = "cardSet.id")
	Flashcard fromDtoToEntity(FlashcardDto dto);
	
	@InheritInverseConfiguration(name="fromDtoToEntity")
	FlashcardDto fromEntityToDto(Flashcard entity);
}
