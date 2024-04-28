package com.apica.assignment.UserManagement.Services;

import com.apica.assignment.UserManagement.Repository.JournalRepository;
import com.apica.assignment.UserManagement.Model.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;


    // Method to retrieve all journals
    public List<Journal> getAllJournals() {
        return journalRepository.JournalList();
    }

    // Method to retrieve a specific journal by ID
    public Journal getJournalById(Long journalId) {
        return journalRepository.findById(journalId);
    }


}
