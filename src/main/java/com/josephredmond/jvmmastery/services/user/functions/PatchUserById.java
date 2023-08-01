package com.josephredmond.jvmmastery.services.user.functions;

import com.josephredmond.jvmmastery.dto.user.UserDTO;
import com.josephredmond.jvmmastery.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class PatchUserById implements BiFunction<UUID, UserDTO, Boolean> {
    private final UserRepository userRepository;
    @Override
    public Boolean apply(UUID id, UserDTO user) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if (id == null) {
            return atomicBoolean.get();
        }
        userRepository.findById(id).ifPresent((userToPatch) -> {
            if (user.getFirstName() != null) {
                userToPatch.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                userToPatch.setLastName(user.getLastName());
            }
            if (user.getEmail() != null) {
                userToPatch.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                userToPatch.setPassword(user.getPassword());
            }
            userRepository.save(userToPatch);
            atomicBoolean.set(true);
        });
        return atomicBoolean.get();
    }
}
