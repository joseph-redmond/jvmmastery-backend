package com.josephredmond.jvmmastery.services.user.functions;

import com.josephredmond.jvmmastery.dto.user.UserDTO;
import com.josephredmond.jvmmastery.mapper.user.UserMapper;
import com.josephredmond.jvmmastery.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SaveUser implements Function<UserDTO, UserDTO> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDTO apply(UserDTO userDTO) {
        return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(userDTO)));
    }
}
