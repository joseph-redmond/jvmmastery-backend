package com.josephredmond.jvmmastery.services.user.functions;

import com.josephredmond.jvmmastery.dto.UserDTO;
import com.josephredmond.jvmmastery.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class UpdateUserById implements BiFunction<UUID, UserDTO, Boolean> {
    private final UserRepository userRepository;

    @Override
    public Boolean apply(UUID id, UserDTO user) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if (id == null) {
            return atomicBoolean.get();
        }
        userRepository.findById(id).ifPresent((userToUpdate) -> {
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPassword(user.getPassword());
            userRepository.save(userToUpdate);
            atomicBoolean.set(true);
        });
        return atomicBoolean.get();
    }
}
