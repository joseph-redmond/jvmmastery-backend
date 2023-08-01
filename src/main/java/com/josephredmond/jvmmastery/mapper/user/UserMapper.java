package com.josephredmond.jvmmastery.mapper.user;

import com.josephredmond.jvmmastery.domain.user.User;
import com.josephredmond.jvmmastery.dto.user.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userDtoToUser(UserDTO userDto);
    UserDTO userToUserDto(User user);
}
