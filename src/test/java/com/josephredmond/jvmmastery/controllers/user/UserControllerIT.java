package com.josephredmond.jvmmastery.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josephredmond.jvmmastery.dto.user.UserDTO;
import com.josephredmond.jvmmastery.services.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerIT {

    @Autowired
    UserController userController;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {

    }


    @Test
    @Order(0)
    void getUserById() throws Exception {
        UserDTO testUser = userService.findAll().get(0);

        ResponseEntity<UserDTO> responseEntity = userController.getUserById(testUser.getId());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testUser.getId(), responseEntity.getBody().getId());
    }

    @Test
    @Order(0)
    void getUserByIdNotFound() throws Exception {
        ResponseEntity<UserDTO> responseEntity = userController.getUserById(UUID.randomUUID());

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }


    @Test
    @Order(1)
    void getAllUsers() throws Exception {
        List<UserDTO> testUserList = userService.findAll();

        ResponseEntity<List<UserDTO>> responseEntity = userController.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testUserList.size(), responseEntity.getBody().size());
    }

    @Test
    @Order(2)
    void updateUser() throws Exception {
        UserDTO user = userService.findAll().get(0);
        user.setFirstName("New Name");

        ResponseEntity<Void> responseEntity = userController.updateUserById(user.getId(), user);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(user.getFirstName(), userService.findById(user.getId()).get().getFirstName());
    }

    @Test
    @Order(2)
    void updateUserNotFound() throws Exception {
        UserDTO user = userService.findAll().get(0);
        user.setFirstName("New Name");

        ResponseEntity<Void> responseEntity = userController.updateUserById(UUID.randomUUID(), user);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @Order(3)
    void patchUser() throws Exception {
        UserDTO user = userService.findAll().get(0);
        user.setFirstName("New Name");

        ResponseEntity<Void> responseEntity = userController.patchUserById(user.getId(), user);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(user.getFirstName(), userService.findById(user.getId()).get().getFirstName());
    }

    @Test
    @Order(3)
    void patchUserNotFonud() throws Exception {
        UserDTO user = userService.findAll().get(0);
        user.setFirstName("New Name");

        ResponseEntity<Void> responseEntity = userController.patchUserById(UUID.randomUUID(), user);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @Order(4)
    void deleteUser() throws Exception {
        UserDTO user = userService.findAll().get(0);

        ResponseEntity<Void> responseEntity = userController.deleteUserById(user.getId());

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(2, userService.findAll().size());
    }

    @Test
    @Order(4)
    void deleteUserNotFound() throws Exception {
        ResponseEntity<Void> responseEntity = userController.deleteUserById(UUID.randomUUID());

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(3, userService.findAll().size());
    }

    @Test
    @Order(999)
    void createUser() throws Exception {
        UserDTO user = userService.findAll().get(0);
        user.setId(null);

        ResponseEntity<UserDTO> responseEntity = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(user.getFirstName(), responseEntity.getBody().getFirstName());
        assertEquals(user.getLastName(), responseEntity.getBody().getLastName());
        assertEquals(user.getEmail(), responseEntity.getBody().getEmail());

    }
}