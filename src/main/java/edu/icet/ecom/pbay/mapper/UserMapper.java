package edu.icet.ecom.pbay.mapper;

import org.mapstruct.Mapper;

import edu.icet.ecom.pbay.dto.UserDto;
import edu.icet.ecom.pbay.entity.UserEntity;

@Mapper(componentModel="spring")
public interface UserMapper {
    UserDto toDto(UserEntity userEntity);
    UserEntity toEntity(UserDto userDto);
}
