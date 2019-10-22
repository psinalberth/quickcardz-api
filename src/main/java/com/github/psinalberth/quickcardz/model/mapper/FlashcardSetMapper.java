package com.github.psinalberth.quickcardz.model.mapper;

import org.mapstruct.Mapper;

import com.github.psinalberth.quickcardz.model.entity.FlashcardSet;
import com.github.psinalberth.quickcardz.model.vo.FlashcardSetVO;

@Mapper
public interface FlashcardSetMapper extends IAbstractMapper<FlashcardSetVO, FlashcardSet>  {
	
}
