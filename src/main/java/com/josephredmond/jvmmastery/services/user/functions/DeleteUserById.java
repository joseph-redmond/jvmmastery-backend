package com.josephredmond.jvmmastery.services.user.functions;

import com.josephredmond.jvmmastery.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class DeleteUserById implements Function<UUID, Boolean> {
    private final UserRepository userRepository;
    @Override
    public Boolean apply(UUID id) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if (id == null) {
            return atomicBoolean.get();
        }
        userRepository.findById(id).ifPresent((user) -> {
            userRepository.deleteById(user.getId());
            atomicBoolean.set(true);
        });
        return atomicBoolean.get();
    }
}
