package com.josephredmond.jvmmastery.mapper;

import com.josephredmond.jvmmastery.domain.User;
import com.josephredmond.jvmmastery.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userDtoToUser(UserDTO userDto);
    UserDTO userToUserDto(User user);
}
