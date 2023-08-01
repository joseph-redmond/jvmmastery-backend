package com.josephredmond.jvmmastery.bootstrap;

import com.josephredmond.jvmmastery.domain.user.User;
import com.josephredmond.jvmmastery.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserBootstrap implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        initUsers();
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            final User user1 = User.builder()
                    .firstName("Joe")
                    .lastName("Redmond")
                    .email("me@test.com")
                    .password("password")
                    .build();

            final User user2 = User.builder()
                    .firstName("Jane")
                    .lastName("Doe")
                    .email("jane@janodoe.com")
                    .password("password")
                    .build();

            final User user3 = User.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .email("john@johndoe.com")
                    .password("password")
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        }
    }
}
