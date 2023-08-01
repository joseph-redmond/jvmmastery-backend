package com.josephredmond.jvmmastery.controllers.user;

import com.josephredmond.jvmmastery.dto.user.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserController {

    ResponseEntity<UserDTO> getUserById(UUID id);
    ResponseEntity<List<UserDTO>> getAllUsers();
    ResponseEntity<UserDTO> createUser(UserDTO user);
    ResponseEntity<Void> updateUserById(UUID id, UserDTO user);
    ResponseEntity<Void> patchUserById(UUID id, UserDTO user);
    ResponseEntity<Void> deleteUserById(UUID id);
}
