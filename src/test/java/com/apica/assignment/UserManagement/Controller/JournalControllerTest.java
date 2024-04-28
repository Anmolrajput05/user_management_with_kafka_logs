package com.apica.assignment.UserManagement.Controller;

import com.apica.assignment.UserManagement.Model.Journal;
import com.apica.assignment.UserManagement.Services.JournalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JournalControllerTest {

    @Mock
    private JournalService journalService;

    @InjectMocks
    private JournalController journalController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllJournals() {
        // Mocking journal data
        List <Journal> journals = new ArrayList<>();
        journals.add(new Journal(1L, "Journal 1"));
        journals.add(new Journal(2L, "Journal 2"));

        // Mocking the behavior of journalService.getAllJournals() method
        when(journalService.getAllJournals()).thenReturn(journals);

        // Calling the controller method
        List <Journal> result = journalController.getAllJournals();

        // Verifying that journalService.getAllJournals() method is called
        verify(journalService, times(1)).getAllJournals();

        // Verifying the result
        assertEquals(2, result.size());
        assertEquals("Journal 1", result.get(0).getMessage());
        assertEquals("Journal 2", result.get(1).getMessage());
    }

    @Test
    public void testGetJournalById() {
        // Mocking a journal
        Journal journal = new Journal(1L, "Journal 1");

        // Mocking the behavior of journalService.getJournalById() method
        when(journalService.getJournalById(1L)).thenReturn(journal);

        // Calling the controller method
        Journal result = journalController.getJournalById(1L);

        // Verifying that journalService.getJournalById() method is called
        verify(journalService, times(1)).getJournalById(1L);

        // Verifying the result
        assertEquals("Journal 1", result.getMessage());
    }
}


