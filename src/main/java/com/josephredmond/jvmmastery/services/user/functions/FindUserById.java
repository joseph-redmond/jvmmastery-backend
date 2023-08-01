package com.josephredmond.jvmmastery.services.user.functions;

import com.josephredmond.jvmmastery.dto.UserDTO;
import com.josephredmond.jvmmastery.mapper.UserMapper;
import com.josephredmond.jvmmastery.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class FindUserById implements Function<UUID, Optional<UserDTO>> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public Optional<UserDTO> apply(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        return userRepository.findById(id).map(userMapper::userToUserDto);

    }
}
