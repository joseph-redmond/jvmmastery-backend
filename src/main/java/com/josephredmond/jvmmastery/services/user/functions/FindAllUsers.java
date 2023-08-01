package com.josephredmond.jvmmastery.services.user.functions;

import com.josephredmond.jvmmastery.dto.UserDTO;
import com.josephredmond.jvmmastery.mapper.UserMapper;
import com.josephredmond.jvmmastery.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class FindAllUsers implements Supplier<List<UserDTO>> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public List<UserDTO> get() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).toList();
    }
}
