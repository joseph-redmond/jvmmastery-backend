package com.josephredmond.jvmmastery.services.user;

import com.josephredmond.jvmmastery.dto.user.UserDTO;
import com.josephredmond.jvmmastery.services.user.functions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceJpa implements UserService {
    private final FindUserById findUserById;
    private final FindAllUsers findAllUsers;
    private final SaveUser saveUser;
    private final DeleteUserById deleteUserById;
    private final UpdateUserById updateUserById;
    private final PatchUserById patchUserById;
    @Override
    public Optional<UserDTO> findById(UUID id) {
        return findUserById.apply(id);
    }

    @Override
    public List<UserDTO> findAll() {
        return findAllUsers.get();
    }

    @Override
    public UserDTO save(UserDTO user) {
        return saveUser.apply(user);
    }

    @Override
    public Boolean deleteById(UUID id) {
        return deleteUserById.apply(id);
    }

    @Override
    public Boolean updateById(UUID id, UserDTO user) {
        return updateUserById.apply(id, user);
    }

    @Override
    public Boolean patchById(UUID id, UserDTO user) {
        return patchUserById.apply(id, user);
    }
}
