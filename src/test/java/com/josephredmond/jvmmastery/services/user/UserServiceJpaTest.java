package com.josephredmond.jvmmastery.services.user;

import com.josephredmond.jvmmastery.bootstrap.UserBootstrap;
import com.josephredmond.jvmmastery.domain.user.User;
import com.josephredmond.jvmmastery.dto.user.UserDTO;
import com.josephredmond.jvmmastery.mapper.user.UserMapperImpl;
import com.josephredmond.jvmmastery.repositories.user.UserRepository;
import com.josephredmond.jvmmastery.services.user.functions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({
        UserServiceJpa.class,
        UserBootstrap.class,
        UserMapperImpl.class,
        FindUserById.class,
        FindAllUsers.class,
        UpdateUserById.class,
        SaveUser.class,
        PatchUserById.class,
        DeleteUserById.class

})
class UserServiceJpaTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getUserById() {
        User user = userRepository.findAll().get(0);
        Optional<UserDTO> userOptional = userService.findById(user.getId());
        assertTrue(userOptional.isPresent());
        assertEquals(user.getId(), userOptional.get().getId());
    }

    @Test
    void getUserByIdNotFound() {
        Optional<UserDTO> userOptional = userService.findById(UUID.randomUUID());
        assertFalse(userOptional.isPresent());
    }

    @Test
    void getUserByIdNull() {
        Optional<UserDTO> userOptional = userService.findById(null);
        assertFalse(userOptional.isPresent());
    }

    @Test
    void getAllUsers() {
        assertEquals(3, userService.findAll().size());
    }

    @Test
    void saveUser() {
        UserDTO user = new UserDTO();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("me@test.com");
        user.setPassword("password");
        UserDTO savedUser = userService.save(user);
        assertNotNull(savedUser.getId());
        assertEquals(user.getFirstName(), savedUser.getFirstName());
        assertEquals(user.getLastName(), savedUser.getLastName());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPassword(), savedUser.getPassword());
    }

    @Test
    void deleteUserById() {
        User user = userRepository.findAll().get(0);
        assertTrue(userService.deleteById(user.getId()));
        assertEquals(2, userRepository.findAll().size());
    }

    @Test
    void deleteUserByIdNotFound() {
        assertFalse(userService.deleteById(UUID.randomUUID()));
    }

    @Test
    void deleteUserByIdNull() {
        assertFalse(userService.deleteById(null));
    }

    @Test
    void updateUserById() {
        User user = userRepository.findAll().get(0);
        UserDTO userToUpdate = new UserDTO();
        userToUpdate.setFirstName("Test");
        userToUpdate.setLastName("User");
        userToUpdate.setEmail("newtest@test.com");
        userToUpdate.setPassword("newpassword");

        assertTrue(userService.updateById(user.getId(), userToUpdate));

        User updatedUser = userRepository.findById(user.getId()).get();
        assertEquals(userToUpdate.getFirstName(), updatedUser.getFirstName());
        assertEquals(userToUpdate.getLastName(), updatedUser.getLastName());
        assertEquals(userToUpdate.getEmail(), updatedUser.getEmail());
        assertEquals(userToUpdate.getPassword(), updatedUser.getPassword());
    }

    @Test
    void updateUserByIdNotFound() {
        UserDTO userToUpdate = new UserDTO();
        userToUpdate.setFirstName("Test");
        userToUpdate.setLastName("User");
        userToUpdate.setEmail("newUser@test.com");
        userToUpdate.setPassword("newpassword");

        assertFalse(userService.updateById(UUID.randomUUID(), userToUpdate));
    }

    @Test
    void updateUserByIdNull() {
        UserDTO userToUpdate = new UserDTO();
        userToUpdate.setFirstName("Test");
        userToUpdate.setLastName("User");
        userToUpdate.setEmail("newUser@test.com");
        userToUpdate.setPassword("newpassword");

        assertFalse(userService.updateById(null, userToUpdate));
    }

    @Test
    void patchUserById() {
        User user = userRepository.findAll().get(0);
        UserDTO userToUpdate = new UserDTO();
        userToUpdate.setEmail("testUser@test.com");
        userToUpdate.setPassword("newpassword");

        assertTrue(userService.patchById(user.getId(), userToUpdate));
    }

    @Test
    void patchUserByIdNotFound() {
        UserDTO userToUpdate = new UserDTO();
        userToUpdate.setEmail("test@test.com");
        userToUpdate.setPassword("newpassword");

        assertFalse(userService.patchById(UUID.randomUUID(), userToUpdate));
    }

    @Test
    void patchUserByIdNull() {
        UserDTO userToUpdate = new UserDTO();
        userToUpdate.setEmail("test@test.com");

        assertFalse(userService.patchById(null, userToUpdate));
    }
}