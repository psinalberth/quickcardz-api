package com.github.psinalberth.quickcardz.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.psinalberth.quickcardz.model.entity.User;
import com.github.psinalberth.quickcardz.model.vo.UserRegistrationDto;

@Mapper(uses = PasswordEncoderMapper.class)
public interface UserMapper extends IAbstractMapper<User, UserRegistrationDto> {
	
	@Override
	@Mapping(target = "password", qualifiedByName = "encoder")
	User fromDtoToEntity(UserRegistrationDto dto);
	
	@Override
	@Mapping(target = "password", ignore = true)
	UserRegistrationDto fromEntityToDto(User entity);
}
