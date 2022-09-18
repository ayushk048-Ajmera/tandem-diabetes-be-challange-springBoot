package com.backendchallenge.mapper;

import com.backendchallenge.dtos.UserRequestDTO;
import com.backendchallenge.dtos.UserResponseDTO;
import com.backendchallenge.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
	UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

	UserEntity toEntity(UserRequestDTO userRequestDTO);

	@Mapping(source = "userEntity.id", target = "userId")
	@Mapping(source = "userEntity", target = "name", qualifiedByName = "formatName")
	UserResponseDTO toResponseDTO(UserEntity userEntity);

	@Named("formatName")
	default String formatName(UserEntity userEntity) {
		return String.format("%s %s %s", userEntity.getFirstName(), userEntity.getMiddleName(), userEntity.getLastName());
	}
}
