package com.github.psinalberth.quickcardz.model.mapper;

public interface IAbstractMapper<E, D> {
	
	 public <E, D>D fromEntityToDto(E entity, D dto);
	 
	 public <D, E>E fromDtoToEntity(D dto, E entity);
}
