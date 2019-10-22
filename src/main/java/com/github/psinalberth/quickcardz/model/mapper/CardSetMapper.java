package com.github.psinalberth.quickcardz.model.mapper;

import org.mapstruct.Mapper;

import com.github.psinalberth.quickcardz.model.entity.CardSet;
import com.github.psinalberth.quickcardz.model.vo.CardSetDto;

@Mapper
public interface CardSetMapper extends IAbstractMapper<CardSet, CardSetDto> {

}
