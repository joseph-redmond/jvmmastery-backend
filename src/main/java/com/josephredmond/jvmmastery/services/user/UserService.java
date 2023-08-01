package com.josephredmond.jvmmastery.services.user;

import com.josephredmond.jvmmastery.dto.user.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<UserDTO> findById(UUID id);
    List<UserDTO> findAll();
    UserDTO save(UserDTO user);
    Boolean deleteById(UUID id);
    Boolean updateById(UUID id, UserDTO user);
    Boolean patchById(UUID id, UserDTO user);
}
