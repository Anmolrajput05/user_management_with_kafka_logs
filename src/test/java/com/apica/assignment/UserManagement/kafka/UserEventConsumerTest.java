package com.apica.assignment.UserManagement.kafka;

import com.apica.assignment.UserManagement.Repository.JournalRepository;
import com.apica.assignment.UserManagement.Model.Journal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserEventConsumerTest {

    @Mock
    private JournalRepository journalRepository;

    @InjectMocks
    private UserEventConsumer userEventConsumer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsume() {
        // Mocking the message
        String message = "Test message";

        // Mocking the getAllUser() method of journalRepository to return a list of journals
        List         <Journal> journals = new ArrayList<>();
        when(journalRepository.JournalList()).thenReturn(journals);

        // Calling the consume method
        userEventConsumer.consume(message);

        // Verifying that the message is added to the list of journals
        assertEquals(1, journals.size());
        assertEquals(message, journals.get(0).getMessage());
    }
}

