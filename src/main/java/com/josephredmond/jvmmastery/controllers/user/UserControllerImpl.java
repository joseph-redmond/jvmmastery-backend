package com.josephredmond.jvmmastery.controllers.user;

import com.josephredmond.jvmmastery.dto.user.UserDTO;
import com.josephredmond.jvmmastery.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    public static final String USER_PATH = "/api/v1/user";
    public static final String USER_PATH_ID = USER_PATH + "/{userId}";


    @Override
    @GetMapping(USER_PATH_ID)
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") UUID id) {
        AtomicReference<ResponseEntity<UserDTO>> responseEntity = new AtomicReference<>(ResponseEntity.notFound().build());
        userService.findById(id).ifPresent((user) -> {
            responseEntity.set(ResponseEntity.ok(user));
        });
        return responseEntity.get();
    }

    @Override
    @GetMapping(USER_PATH)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @Override
    @PostMapping(USER_PATH)
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody UserDTO user) {
        UserDTO savedUser = userService.save(user);

        return ResponseEntity.created(URI.create(USER_PATH + "/" + savedUser.getId())).body(savedUser);
    }

    @Override
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") UUID id, @Validated @RequestBody UserDTO user) {
        Boolean result = userService.updateById(id, user);
        if (result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> patchUserById(UUID id, UserDTO user) {
        Boolean result = userService.patchById(id, user);
        if (result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteUserById(UUID id) {
        Boolean result = userService.deleteById(id);
        if (result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
