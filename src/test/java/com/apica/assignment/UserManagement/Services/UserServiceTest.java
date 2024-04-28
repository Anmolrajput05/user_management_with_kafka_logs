package com.apica.assignment.UserManagement.Services;

import com.apica.assignment.UserManagement.Repository.UserRepo;
import com.apica.assignment.UserManagement.kafka.UserEventProducer;
import com.apica.assignment.UserManagement.Model.Type;
import com.apica.assignment.UserManagement.Model.User;
import com.apica.assignment.UserManagement.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private UserEventProducer userEventProducer;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUser() {
        // Mocking the behavior of userRepo.getUsers() method to return a list of users
        List<User> users = new ArrayList<>();
        when(userRepo.getUsers()).thenReturn(users);

        // Calling the getAllUser() method
        List<User> result = userService.getAllUser();

        // Verifying that userRepo.getUsers() method is called
        verify(userRepo, times(1)).getUsers();

        // Verifying the result
        assertEquals(users, result);
    }

    @Test
    public void testInputUsers() {
        // Mocking the behavior of getAllUser() method to return an empty list initially
        when(userRepo.getUsers()).thenReturn(new ArrayList<>());

        // Mocking the behavior of sendUserEvent() method
        doNothing().when(userEventProducer).sendUserEvent(anyString());

        // Creating a list of users to input
        List<User> usersToAdd = new ArrayList<>();
        usersToAdd.add(new User(1, "John Doe", "john@example.com", "1234567890"));
        usersToAdd.add(new User(2, "Jane Smith", "jane@example.com", "9876543210"));

        // Calling the inputUsers() method
        String result = userService.inputUsers(usersToAdd);

        // Verifying that userRepo.getUsers() method is called to retrieve all users
        verify(userRepo, times(1)).getUsers();

        // Verifying that userEventProducer.sendUserEvent() method is called with the concatenated usernames
        verify(userEventProducer, times(1)).sendUserEvent("Users registered: John Doe, Jane Smith");

        // Verifying the result
        assertEquals("Users added", result);
    }

    @Test
    public void testInputUser() {
        // Mocking the behavior of getAllUser() method to return an empty list initially
        when(userRepo.getUsers()).thenReturn(new ArrayList<>());

        // Calling the inputUser() method
        String result = userService.inputUser(new User(1, "John Doe", "john@example.com", "1234567890"));

        // Verifying that userRepo.getUsers() method is called to retrieve all users
        verify(userRepo, times(1)).getUsers();

        // Verifying the result
        assertEquals("User added", result);
    }

    @Test
    public void testGetUser() {
        // Mocking the behavior of getAllUser() method to return a list of users
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe", "john@example.com", "1234567890"));
        users.add(new User(2, "Jane Smith", "jane@example.com", "9876543210"));
        when(userRepo.getUsers()).thenReturn(users);

        // Calling the getUser() method
        User result = userService.getUser(1);

        // Verifying that userRepo.getUsers() method is called to retrieve all users
        verify(userRepo, times(1)).getUsers();

        // Verifying the result
        assertEquals(users.get(0), result);
    }
    @Test
    public void testUpdateUserNumber() {
        // Mocking the behavior of getAllUser() method to return a list of users
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe", "john@example.com", "1234567890"));
        when(userRepo.getUsers()).thenReturn(users);

        // Calling the updateUserNumber() method
        String result = userService.updateUserNumber(1, "9876543210");

        // Verifying that userRepo.getUsers() method is called to retrieve all users
        verify(userRepo, times(1)).getUsers();

        // Verifying the result
        assertEquals("User Contact No Updated.", result);
        assertEquals("9876543210", users.get(0).getUserContactNo());
    }

    // Test for updateUserEmailAddress method
    @Test
    public void testUpdateUserEmailAddress() {
        // Mocking the behavior of getAllUser() method to return a list of users
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe", "john@example.com", "1234567890"));
        when(userRepo.getUsers()).thenReturn(users);

        // Calling the updateUserEmailAddress() method
        String result = userService.updateUserEmailAddress(1, "updated@example.com");

        // Verifying that userRepo.getUsers() method is called to retrieve all users
        verify(userRepo, times(1)).getUsers();

        // Verifying the result
        assertEquals("User's Address Updated.", result);
        assertEquals("updated@example.com", users.get(0).getUserEmail());
    }

    // Test for updateRole method
    @Test
    public void testUpdateRole() {
        // Mocking the behavior of getAllUser() method to return a list of users
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe", "john@example.com", "1234567890"));
        when(userRepo.getUsers()).thenReturn(users);

        // Calling the updateRole() method
        String result = userService.updateRole(1, Type.ADMIN);

        // Verifying that userRepo.getUsers() method is called to retrieve all users
        verify(userRepo, times(1)).getUsers();

        // Verifying the result
        assertEquals("User's role Updated.", result);
        assertEquals(Type.ADMIN, users.get(0).getType());
    }

    // Test for deleteUser method
    @Test
    public void testDeleteUser() {
        // Mocking the behavior of getAllUser() method to return a list of users
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe", "john@example.com", "1234567890"));
        when(userRepo.getUsers()).thenReturn(users);

        // Calling the deleteUser() method
        String result = userService.deleteUser(1);

        // Verifying that userRepo.getUsers() method is called to retrieve all users
        verify(userRepo, times(1)).getUsers();

        // Verifying the result
        assertEquals("User removed.", result);
        assertTrue(users.isEmpty());
    }
}
