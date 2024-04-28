package com.apica.assignment.UserManagement.Services;

import com.apica.assignment.UserManagement.Repository.JournalRepository;
import com.apica.assignment.UserManagement.Model.Journal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JournalServiceTest {

    @Mock
    private JournalRepository journalRepository;

    @InjectMocks
    private JournalService journalService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllJournals() {
        // Mocking the behavior of journalRepository.JournalList() method to return a list of journals
        List <Journal> journals = new ArrayList<>();
        when(journalRepository.JournalList()).thenReturn(journals);

        // Calling the getAllJournals() method
        List <Journal> result = journalService.getAllJournals();

        // Verifying that journalRepository.JournalList() method is called
        verify(journalRepository, times(1)).JournalList();

        // Verifying the result
        assertEquals(journals, result);
    }

    @Test
    public void testGetJournalById() {
        // Mocking the behavior of journalRepository.findById() method to return a journal
        Long journalId = 1L;
        Journal journal = new Journal(journalId, "Test Journal");
        when(journalRepository.findById(journalId)).thenReturn((journal));

        // Calling the getJournalById() method
        Journal result = journalService.getJournalById(journalId);

        // Verifying that journalRepository.findById() method is called
        verify(journalRepository, times(1)).findById(journalId);

        // Verifying the result
        assertEquals(journal, result);
    }
}


