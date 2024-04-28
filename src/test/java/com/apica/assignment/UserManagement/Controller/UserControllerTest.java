package com.apica.assignment.UserManagement.Controller;

import com.apica.assignment.UserManagement.Model.Type;
import com.apica.assignment.UserManagement.Model.User;
import com.apica.assignment.UserManagement.Services.UserService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser_ValidUser() {
        // Create a valid user
        User user = new User();
        user.setUserId(1);
        user.setUserName("John Doe");
        user.setUserEmail("john@example.com");
        user.setUserContactNo("911234567890");

        // Mock userService.inputUser() method to return a success message
        when(userService.inputUser(any(User.class))).thenReturn("User added successfully");

        // Call the controller method
        String response = userController.addUser(user);


        assertEquals("User added successfully", response);
    }

    @Test
    public void testAddUser_InvalidUser() {
        // Create an invalid user (e.g., missing required fields)
        User user = new User();

        // Call the controller method
        String response = userController.addUser(user);

    }

    @Test
    public void testGetAllUsers() {
        // Mock userService.getAllUser() method to return a list of users
        List<User> users = Arrays.asList(
                new User(1, "John Doe", "john@example.com", "911234567890"),
                new User(2, "Jane Smith", "jane@example.com", "912345678901")
        );
        when(userService.getAllUser()).thenReturn(users);

        // Call the controller method
        List<User> result = userController.getAllUsers();

        // Verify the result
        assertEquals(2, result.size());
    }

    @Test
    public void testGetUserById() {
        // Mock userService.getUser() method to return a user
        User user = new User(1, "John Doe", "john@example.com", "911234567890");
        when(userService.getUser(1)).thenReturn(user);

        // Call the controller method
        User result = userController.getUserById(1);

        // Verify the result
        assertEquals(user, result);
    }

    @Test
    public void testUpdateUserNumById() {
        // Mock userService.updateUserNumber() method to return a success message
        when(userService.updateUserNumber(1, "911234567890")).thenReturn("Phone number updated successfully");

        // Call the controller method
        String response = userController.updateUserNumById(1, "911234567890");

        assertEquals("Phone number updated successfully", response);
    }

    @Test
    public void testUpdateUserAddressById() {
        // Mock userService.updateUserEmailAddress() method to return a success message
        when(userService.updateUserEmailAddress(1, "john@example.com")).thenReturn("Email address updated successfully");

        // Call the controller method
        String response = userController.updateUserAddressById(1, "john@example.com");

        // Verify the response
        assertEquals("Email address updated successfully", response);
    }

    @Test
    public void testDeleteUserById() {
        // Mock userService.deleteUser() method to return a success message
        when(userService.deleteUser(1)).thenReturn("User deleted successfully");

        // Call the controller method
        String response = userController.deleteUserById(1);

        // Verify the response
        assertEquals("User deleted successfully", response);
    }

    @Test
    public void testUpdateUserAddressByIdWithType() {
        // Mock userService.updateRole() method to return a success message
        when(userService.updateRole(1, Type.ADMIN)).thenReturn("User role updated successfully");

        // Call the controller method
        String response = userController.updateUserAddressById(1, Type.ADMIN);

        // Verify the response
        assertEquals("User role updated successfully", response);
    }
}
