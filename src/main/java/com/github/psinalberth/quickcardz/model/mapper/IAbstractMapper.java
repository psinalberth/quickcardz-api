package com.github.psinalberth.quickcardz.model.mapper;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface IAbstractMapper<E, D> {
	
	 public D fromEntityToDto(E entity);
	 
	 public E fromDtoToEntity(D dto);
	 
	 @Mapping(target = "id", ignore = true)
	 E update(@MappingTarget E entityToUpdate, D updatedDto);
	 
	 List<D> map(List<E> entities);
	 
	 List<D> map(Iterable<E> entities);
}
