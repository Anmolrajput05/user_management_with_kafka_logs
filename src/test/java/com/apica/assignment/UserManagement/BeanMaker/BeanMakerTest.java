package com.apica.assignment.UserManagement.BeanMaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.apica.assignment.UserManagement.BeanMaker.BeanMaker;
import com.apica.assignment.UserManagement.Model.Journal;
import com.apica.assignment.UserManagement.Model.User;

@SpringBootTest
public class BeanMakerTest {

    @Autowired
    private BeanMaker beanMaker;

    @Test
    public void testGetUsers() {
        List<User> users = beanMaker.getUsers();
        assertNotNull(users);
        assertEquals(0, users.size());
    }

    @Test
    public void testGetJournal() {
        List<Journal> journal = beanMaker.getJournal();
        assertNotNull(journal);
        assertEquals(0, journal.size());
    }
}
